package com.smart_cities.city.repository;

import com.smart_cities.city.model.AggregatedConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AggregatedConsumptionRepository extends JpaRepository<AggregatedConsumption, Long> {
    Optional<AggregatedConsumption> findFirstByProviderIdOrderByConsumptionPeriodEnd(Long providerId);

}
