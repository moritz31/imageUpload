package de.darmotek.imageUpload;

import de.darmotek.imageUpload.Service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class App implements CommandLineRunner{

    @Resource
    StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		this.storageService.init();
    }
}
