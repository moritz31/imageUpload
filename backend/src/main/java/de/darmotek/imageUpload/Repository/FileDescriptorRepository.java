package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.FileDescriptor;
import org.springframework.data.repository.CrudRepository;

public interface FileDescriptorRepository extends CrudRepository<FileDescriptor, Long> {

    FileDescriptor findByPath(String path);

}
