package com.nortic.pos.es.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UsEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsEurekaServerApplication.class, args);
    }

}
