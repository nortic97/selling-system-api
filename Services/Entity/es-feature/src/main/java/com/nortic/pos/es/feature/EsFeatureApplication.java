package com.nortic.pos.es.feature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EsFeatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsFeatureApplication.class, args);
	}

}
