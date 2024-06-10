package com.nortic.posdash.es.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.nortic.posdash")
@EnableJpaRepositories(basePackages = "com.nortic.posdash.es.users.repository")
public class UsersEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersEsApplication.class, args);
	}

}
