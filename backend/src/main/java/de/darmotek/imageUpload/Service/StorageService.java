package de.darmotek.imageUpload.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StorageService {

    private static final Logger logger = Logger.getLogger(StorageService.class.getName());
    private final Path rootLocation = Paths.get("upload-dir");
    public void store(MultipartFile file ) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {

        try {
            Path file = this.rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
    }

    public void init() {
        try {
            if (!Files.exists(this.rootLocation)) {
                Files.createDirectory(this.rootLocation);
            }

        } catch(IOException e) {
            throw new RuntimeException("Failed to init storage");
        }
    }

    public List<String> getCurrentFiles() {
        File folder = new File(this.rootLocation.toUri());
        File[] listOfFiles = folder.listFiles();
        List<String> fileListByName = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                fileListByName.add("/api/files/" + file.getName());
            }
        }

        return fileListByName;
    }

}
