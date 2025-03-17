package com.nortic.posdash.us.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nortic.posdash.*")
public class AuthUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUsApplication.class, args);
    }

}
