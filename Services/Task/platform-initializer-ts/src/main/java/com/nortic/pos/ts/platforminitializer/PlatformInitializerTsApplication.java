package com.nortic.pos.ts.platforminitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.nortic.pos.*")
@SpringBootApplication
public class PlatformInitializerTsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformInitializerTsApplication.class, args);
	}

}
