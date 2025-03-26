package com.nortic.pos.es.stores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.nortic.pos.models.*")
@SpringBootApplication
public class StoresEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoresEsApplication.class, args);
    }

}
