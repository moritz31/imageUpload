package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User testUser = new User("test", "test");
        userRepository.save(testUser);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findByUsername() {

        User foundUser = this.userRepository.findByUsername("test");

        assertEquals("test", foundUser.password);

    }
}