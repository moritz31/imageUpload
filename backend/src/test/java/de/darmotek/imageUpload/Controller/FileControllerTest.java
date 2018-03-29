package de.darmotek.imageUpload.Controller;

import de.darmotek.imageUpload.Service.StorageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private StorageService storageService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void uploadFileHandler() {
    }

    @Test
    public void getListFiles() {
    }

    @Test
    public void getFile() throws Exception {
        when(storageService.loadFile("test.jpg")).thenReturn(new UrlResource("test.jpg"));
        mockMvc.perform(get("/api/files/test.jpg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("test.jpg"));
    }
}