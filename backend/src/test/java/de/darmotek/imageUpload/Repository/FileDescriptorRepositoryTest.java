package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.SpringMongoConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringMongoConfig.class})
public class FileDescriptorRepositoryTest {

    @Autowired
    FileDescriptorRepository fileDescriptorRepository;

    @Before
    public void init() {
        fileDescriptorRepository.deleteAll();

    }

    @Test
    public void happyWorld() {

    }

}