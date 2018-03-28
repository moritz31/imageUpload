package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.FileDescriptor;
import de.darmotek.imageUpload.SpringMongoConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileDescriptorRepositoryTest {

    @Autowired
    private FileDescriptorRepository fileDescriptorRepository;

    @Before
    public void init() {
        fileDescriptorRepository.deleteAll();

    }

    @Test
    public void addFileDescriptor() {
        FileDescriptor fileDescriptor = new FileDescriptor("test.jpg");
        this.fileDescriptorRepository.save(fileDescriptor);

        FileDescriptor returnedDescriptor = this.fileDescriptorRepository.findByPath("test.jpg");

        assertEquals(fileDescriptor.id,returnedDescriptor.id);
        assertEquals(fileDescriptor.path,returnedDescriptor.path);
        assertEquals(fileDescriptor.tags,returnedDescriptor.tags);
    }

    @Test
    public void getFileWhichDoesNotExist() {

        FileDescriptor nullDescriptor = this.fileDescriptorRepository.findByPath("test.jpg");

        assertNull(nullDescriptor);
    }

    @Test
    public void addSameFile() {

        FileDescriptor fileDescriptor = new FileDescriptor("test.jpg");

        fileDescriptorRepository.save(fileDescriptor);
        fileDescriptorRepository.save(fileDescriptor);

        long elementsInDB = fileDescriptorRepository.count();

        assertEquals(elementsInDB,1);
    }

}