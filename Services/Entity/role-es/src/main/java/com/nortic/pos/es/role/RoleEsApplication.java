package com.nortic.pos.es.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nortic.pos.models.*")
public class RoleEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleEsApplication.class, args);
    }

}
