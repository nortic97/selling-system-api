package com.nortic.pos.es.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsUserApplication.class, args);
	}

}
