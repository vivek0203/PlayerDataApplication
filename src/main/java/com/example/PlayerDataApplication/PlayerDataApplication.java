package com.example.PlayerDataApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayerDataApplication {
private static final Logger log = LoggerFactory.getLogger(PlayerDataApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PlayerDataApplication.class, args);
		log.info("Welcome To Player Information Application..");
	}

}
