package com.nortic.pos.es.branch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nortic.pos.models.*")
public class BranchEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BranchEsApplication.class, args);
    }

}
