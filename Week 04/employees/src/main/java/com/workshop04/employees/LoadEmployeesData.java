package com.workshop04.employees;

import com.workshop04.employees.model.Employee;
import com.workshop04.employees.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadEmployeesData {
    private static final Logger log = LoggerFactory.getLogger(LoadEmployeesData.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading {}", repository.save(new Employee("Maria Salama", "Lecturer")));
            log.info("Preloading {}", repository.save(new Employee("John Rooksby", "Assistant Professor")));
        };
    }
}