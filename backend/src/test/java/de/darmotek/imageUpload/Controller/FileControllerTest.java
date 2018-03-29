package de.darmotek.imageUpload.Controller;

import de.darmotek.imageUpload.Model.FileDescriptor;
import de.darmotek.imageUpload.Repository.FileDescriptorRepository;
import de.darmotek.imageUpload.Service.StorageService;
import de.darmotek.imageUpload.config.SecurityConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
@Import(SecurityConfig.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private FileDescriptorRepository fileDescriptorRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @WithMockUser
    public void uploadFile() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("test.txt").getFile()));


        doNothing().when(this.storageService).store(firstFile);

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/api/post")
                .file(firstFile)
                .param("name", "filename.txt"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void uploadFileWithoutPermission() throws Exception {

        this.mockMvc.perform(post("/api/post"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void getAllFiles() throws Exception {
        List<String> mockedFiles = Arrays.asList("Hello", "Mock", "Test");

        when(this.storageService.getCurrentFiles()).thenReturn(mockedFiles);
        when(this.fileDescriptorRepository.findByPath("Hello")).thenReturn(new FileDescriptor("Hello"));
        when(this.fileDescriptorRepository.findByPath("Mock")).thenReturn(new FileDescriptor("Mock"));
        when(this.fileDescriptorRepository.findByPath("Test")).thenReturn(new FileDescriptor("Test"));

        mockMvc.perform(get("/api/get"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void tryToGetAllFilesWithoutPermission() throws Exception {

        mockMvc.perform(get("/api/get"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void getFile() throws Exception {

        UrlResource resourceToTest = new UrlResource(Objects.requireNonNull(getClass().getClassLoader().getResource("test.txt")));

        when(storageService.loadFile("test.txt")).thenReturn(resourceToTest);
        mockMvc.perform(get("/api/files/test.txt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Mock"));
    }

    @Test
    public void tryToGetFileWithoutPermission() throws Exception {

        UrlResource resourceToTest = new UrlResource(Objects.requireNonNull(getClass().getClassLoader().getResource("test.txt")));

        when(storageService.loadFile("test.txt")).thenReturn(resourceToTest);
        mockMvc.perform(get("/api/files/test.txt"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}