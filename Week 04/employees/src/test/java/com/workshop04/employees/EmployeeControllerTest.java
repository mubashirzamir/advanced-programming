package com.workshop04.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop04.employees.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetEmployeeById() throws Exception {
        // Arrange
        long employeeId = 1L;
        // Act
        ResultActions result = mockMvc.perform(get("/employees/" + employeeId));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.name").value("Maria Salama"))
                .andExpect(jsonPath("$.role").value("Lecturer"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/employees"));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Maria Salama"))
                .andExpect(jsonPath("$[0].role").value("Lecturer"))
                .andExpect(jsonPath("$[1].name").value("John Rooksby"))
                .andExpect(jsonPath("$[1].role").value("Assistant Professor"));
    }

    @Test
    public void testCreateEmployee() throws Exception {
        // Arrange
        Employee employee = new Employee("Jane Doe", "Professor");
        // Act
        ResultActions result = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        // Assert
        result.andExpect(status().isCreated());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee("Updated Name", "Update Role");
        // Act
        ResultActions result = mockMvc.perform(put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        // Assert
        result.andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        // Arrange
        Long employeeId = 1L;
        // Act
        ResultActions result = mockMvc.perform(delete("/employees/" + employeeId));
        // Assert
        result.andExpect(status().isNoContent());
    }
}
