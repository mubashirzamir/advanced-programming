package com.smart_cities.citizen.repository;

import com.smart_cities.citizen.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Citizen, Long> {
}
