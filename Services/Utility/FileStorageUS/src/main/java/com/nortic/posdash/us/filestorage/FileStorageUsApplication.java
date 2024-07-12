package com.nortic.posdash.us.filestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.nortic.posdash.*")
@SpringBootApplication
public class FileStorageUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageUsApplication.class, args);
	}

}
