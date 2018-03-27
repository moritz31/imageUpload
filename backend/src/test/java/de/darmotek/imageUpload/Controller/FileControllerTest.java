package de.darmotek.imageUpload.Controller;

import de.darmotek.imageUpload.App;
import de.darmotek.imageUpload.Service.StorageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storageService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void accessDeniedWhenNotLoggedIn() throws Exception {


        this.mvc.perform(get("/api/get")).andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void uploadFileHandler() {
    }

    @Test
    public void getListFiles() throws Exception {
        when(storageService.getCurrentFiles()).thenReturn(Arrays.asList("Hello", "Mock"));
        this.mvc.perform(get("/api/get").with(user("user"))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[\"Hello\",\"Mock\"]"));
    }

    @Test
    public void getFile() throws Exception {
        //when(storageService.loadFile("test.jpg")).thenReturn(new UrlResource());
    }
}