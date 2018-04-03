package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.FileDescriptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void getMultipleFilesByTag() {

        String tagsForStandardDescriptor[] = {"Hello", "Tag"};
        String tagsForSimpleDescriptor[] = {"Hello"};
        String tagsForComplexDescriptor[] = {"Hello", "World", "With", "Me"};

        FileDescriptor standardDescriptor = new FileDescriptor("test.jpg", tagsForStandardDescriptor);
        FileDescriptor simpleDescriptor = new FileDescriptor("abc.jpg", tagsForSimpleDescriptor);
        FileDescriptor complexDescriptor = new FileDescriptor("test1.jpg", tagsForComplexDescriptor);

        this.fileDescriptorRepository.save(standardDescriptor);
        this.fileDescriptorRepository.save(simpleDescriptor);
        this.fileDescriptorRepository.save(complexDescriptor);

        FileDescriptor[] foundDescriptors = this.fileDescriptorRepository.findByTags("Hello");
        assertEquals(3, foundDescriptors.length);

        foundDescriptors = this.fileDescriptorRepository.findByTags("World");
        assertEquals(1, foundDescriptors.length);

    }

}