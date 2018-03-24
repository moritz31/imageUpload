package de.darmotek.imageUpload.Controller;

import de.darmotek.imageUpload.Service.StorageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FileUploadController {

    private static final Logger logger = Logger.getLogger(FileUploadController.class.getName());

    @Autowired
    StorageService storageService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFileHandler(@RequestParam("name") String name,
                             @RequestParam("file")MultipartFile file) {

        try {
            storageService.store(file);
            logger.log(Level.INFO,"Sucessfully uploaded " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (Exception e) {
            logger.log(Level.WARNING,"Failed uploading " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAILED");
        }

    }

}
