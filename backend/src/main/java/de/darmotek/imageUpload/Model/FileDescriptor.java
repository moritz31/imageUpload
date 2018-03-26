package de.darmotek.imageUpload.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileDescriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;
    //private List<String> tags;

    public FileDescriptor() {
    }

    public FileDescriptor(String path) {
        this.path = path;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileDescriptor{" +
                "id=" + this.id +
                ", path'" + this.path + '\''
                + '}';
    }

}
