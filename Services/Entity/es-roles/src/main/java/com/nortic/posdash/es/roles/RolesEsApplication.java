package com.nortic.posdash.es.roles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.nortic.posdash.*")
@SpringBootApplication
public class RolesEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RolesEsApplication.class, args);
    }

}
