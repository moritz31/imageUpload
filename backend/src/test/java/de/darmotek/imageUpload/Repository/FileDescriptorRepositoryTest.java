package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.FileDescriptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileDescriptorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FileDescriptorRepository fileDescriptorRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenFindByPath_thenReturnDescriptor() {
        FileDescriptor descriptor = new FileDescriptor("test.jpg");
        entityManager.persist(descriptor);
        entityManager.flush();

        FileDescriptor found = fileDescriptorRepository.findByPath(descriptor.getPath());

        assertThat(found.getPath().equals(descriptor.getPath()));
    }
}