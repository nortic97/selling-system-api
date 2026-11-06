package com.nortic.pos.es.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EsEmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                EsEmployeeApplication.class,
                args
        );
    }

}
