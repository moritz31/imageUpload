package de.darmotek.imageUpload.Controller;

import de.darmotek.imageUpload.Service.StorageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.UrlResource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {

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
    public void uploadFileHandler() {
    }

    @Test
    @WithMockUser
    public void getListFiles() throws Exception {
        List<String> expectedResult = Arrays.asList("Hello", "World");
        when(storageService.getCurrentFiles()).thenReturn(Arrays.asList("Hello", "Mock"));
        this.mvc.perform(get("/api/get").with(user("user"))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().;
    }

    @Test
    public void getFile() throws Exception {
        when(storageService.loadFile("test.jpg")).thenReturn(new UrlResource());
    }
}