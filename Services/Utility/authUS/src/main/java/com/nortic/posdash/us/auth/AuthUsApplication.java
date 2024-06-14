package com.nortic.posdash.us.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.nortic.posdash.us.auth.repository")
@SpringBootApplication(scanBasePackages = "com.nortic.posdash")
public class AuthUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUsApplication.class, args);
    }

}
