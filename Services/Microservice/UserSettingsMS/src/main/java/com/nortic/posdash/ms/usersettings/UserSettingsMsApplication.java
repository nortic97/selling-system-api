package com.nortic.posdash.ms.usersettings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.nortic.posdash.*")
@SpringBootApplication
public class UserSettingsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSettingsMsApplication.class, args);
	}

}
