package com.workshop_05.users_mysql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class UsersMysqlApplicationTests {

	@Test
	void contextLoads() {
	}

}
