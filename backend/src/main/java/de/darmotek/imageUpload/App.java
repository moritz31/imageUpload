package de.darmotek.imageUpload;

import de.darmotek.imageUpload.Service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.annotation.Resource;
import java.io.StringReader;

@SpringBootApplication
public class App implements CommandLineRunner{

    @Resource
    StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
	    //this.storageService.init();
    }
}
