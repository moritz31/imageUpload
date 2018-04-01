package de.darmotek.imageUpload;

import de.darmotek.imageUpload.Model.User;
import de.darmotek.imageUpload.Repository.UserRepository;
import de.darmotek.imageUpload.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
public class App implements CommandLineRunner{

    @Resource
    StorageService storageService;

    @Autowired
    UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		this.storageService.init();

        User user = new User("user", new BCryptPasswordEncoder().encode("test123"));
        this.userRepository.save(user);
    }
}
