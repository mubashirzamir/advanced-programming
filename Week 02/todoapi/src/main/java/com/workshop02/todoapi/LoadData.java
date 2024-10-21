package com.workshop02.todoapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.workshop02.todoapi.model.Todo;
import com.workshop02.todoapi.repository.TodoRepository;

@Configuration
public class LoadData {
	private static final Logger log = LoggerFactory.getLogger(LoadData.class);
	
	@Bean
	CommandLineRunner initDatabase(TodoRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Todo("Programming assignment", true)));
			log.info("Preloading " + repository.save(new Todo("Walk dog", false)));
		};
	}
}
