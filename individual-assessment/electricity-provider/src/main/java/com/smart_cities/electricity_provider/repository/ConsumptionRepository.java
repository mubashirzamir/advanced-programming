package com.smart_cities.electricity_provider.repository;

import com.smart_cities.electricity_provider.model.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
}
