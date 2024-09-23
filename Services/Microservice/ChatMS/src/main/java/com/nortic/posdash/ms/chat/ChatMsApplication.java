package com.nortic.posdash.ms.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.nortic.posdash.*")
@SpringBootApplication
public class ChatMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatMsApplication.class, args);
	}

}
