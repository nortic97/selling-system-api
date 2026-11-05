package com.nortic.pos.es.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EsRoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsRoleApplication.class, args);
	}

}
