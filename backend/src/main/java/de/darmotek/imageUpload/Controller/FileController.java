package de.darmotek.imageUpload.Controller;

import de.darmotek.imageUpload.Model.FileDescriptor;
import de.darmotek.imageUpload.Repository.FileDescriptorRepository;
import de.darmotek.imageUpload.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FileController {

    private static final Logger logger = Logger.getLogger(FileController.class.getName());

    @Autowired
    private StorageService storageService;

    @Autowired
    private FileDescriptorRepository fileDescriptorRepository;

    @PostMapping(value = "/api/post")
    public ResponseEntity<String> uploadFile(@RequestParam("name") String name,
                                             @RequestParam("file") MultipartFile file,
                                             @RequestParam("tags") String[] tags) {

        logger.log(Level.INFO, "Received tags: " + tags);

        try {
            storageService.store(file);
            fileDescriptorRepository.save(new FileDescriptor("/api/files/" + file.getOriginalFilename(), tags));
            logger.log(Level.INFO,"Sucessfully uploaded " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING,"Failed uploading " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAILED");
        }

    }

    @GetMapping("/api/get")
    public ResponseEntity<List<FileDescriptor>> getAllFiles(Model model) {
        List<String> allFiles = storageService.getCurrentFiles();

        List<FileDescriptor> allDescriptors = new ArrayList<>();


        for (String fileName : allFiles) {
            allDescriptors.add(fileDescriptorRepository.findByPath(fileName));
        }

        return ResponseEntity.ok().body(allDescriptors);
    }

    @GetMapping("/api/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
