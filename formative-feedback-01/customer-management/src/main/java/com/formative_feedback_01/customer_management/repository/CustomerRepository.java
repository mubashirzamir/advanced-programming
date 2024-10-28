package com.formative_feedback_01.customer_management.repository;

import com.formative_feedback_01.customer_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
