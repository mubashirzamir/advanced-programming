package com.workshop04.employees;

import com.workshop04.employees.model.Employee;
import com.workshop04.employees.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class EmployeesRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Employee e1 = new Employee("Mubashir", "Lecturer");
        Employee e2 = new Employee("Zamir", "Lecturer");
        employeeRepository.saveAll(List.of(e1, e2));

        // Act
        List<Employee> employees = employeeRepository.findAll();

        // Assert
        assertEquals(2, employees.size());
        assertTrue(employees.contains(e1));
        assertTrue(employees.contains(e2));
    }

    @Test
    public void testFindById() {
        // Arrange
        Employee e3 = new Employee("Joe", "QA");
        employeeRepository.save(e3);
        // Act
        Employee employee = employeeRepository.findById(e3.getId()).orElse(null);
        // Assert
        assertEquals(e3, employee);
    }
}
