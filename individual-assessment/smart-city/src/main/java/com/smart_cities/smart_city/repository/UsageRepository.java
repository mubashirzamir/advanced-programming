package com.smart_cities.smart_city.repository;

import com.smart_cities.smart_city.model.Usage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRepository extends JpaRepository<Usage, Long> {
}
