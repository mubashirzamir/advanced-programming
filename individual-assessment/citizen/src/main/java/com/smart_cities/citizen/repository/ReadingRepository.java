package com.smart_cities.citizen.repository;

import com.smart_cities.citizen.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    // Fetch the most recent reading based on entityId, entityType, and sorted by generatedAt
    Optional<Reading> findFirstByEntityIdAndEntityTypeOrderByGeneratedAtDesc(Long entityId, String entityType);
}
