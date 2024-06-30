package com.nortic.posdash.ts.platforminitializer;

import com.nortic.posdash.ts.platforminitializer.service.PlatformInitializerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan("com.nortic.posdash.*")
@SpringBootApplication
public class PlatformInitializerTsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformInitializerTsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PlatformInitializerServiceImpl platformInitializerService) {
		return args -> {
			platformInitializerService.initializePlatform();
		};
	}

}
