package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.FileDescriptor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileDescriptorRepository extends MongoRepository<FileDescriptor, Long> {

    FileDescriptor findByPath(String path);

    FileDescriptor[] findByTags(String tag);

}
