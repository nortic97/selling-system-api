package com.nortic.posdash.us.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.nortic.posdash.*")
public class AuthUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUsApplication.class, args);
    }

}
