package de.darmotek.imageUpload.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document
public class FileDescriptor {

    @Id
    public String path;
    public List<String> tags;

    public FileDescriptor() {
        this.tags = new ArrayList<>();
    }

    public FileDescriptor(String path) {
        this.path = path;
        this.tags = new ArrayList<>();
    }

    public FileDescriptor(String path, String[] tags) {
        this.path = path;
        this.tags = Arrays.asList(tags);
    }


    @Override
    public String toString() {
        return "FileDescriptor{" +
                ", path=" + this.path +
                ", tags=" + this.tags +
                '}';
    }

}
