package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.FileDescriptor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileDescriptorRepository extends MongoRepository<FileDescriptor, Long> {

    FileDescriptor findByPath(String path);

    List<FileDescriptor> findByTags(String tag);

}
