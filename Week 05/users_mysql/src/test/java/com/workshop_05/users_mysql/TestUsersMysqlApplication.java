package com.workshop_05.users_mysql;

import org.springframework.boot.SpringApplication;

public class TestUsersMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.from(UsersMysqlApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
