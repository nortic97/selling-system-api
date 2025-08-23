package com.nortic.pos.es.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.nortic.pos.models.*")
@SpringBootApplication
public class UsersEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersEsApplication.class, args);
	}

}
