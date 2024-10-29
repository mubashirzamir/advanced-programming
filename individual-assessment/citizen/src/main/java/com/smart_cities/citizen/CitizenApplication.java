package com.smart_cities.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitizenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitizenApplication.class, args);
	}

}
