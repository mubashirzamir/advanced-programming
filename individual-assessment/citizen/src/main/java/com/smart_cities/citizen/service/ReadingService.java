package com.smart_cities.citizen.service;

import com.smart_cities.citizen.contracts.IsAbleToCreateReadings;
import com.smart_cities.citizen.model.Reading;
import com.smart_cities.citizen.repository.ReadingRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReadingService {
    ReadingRepository readingRepository;
    ReadingGenerator readingGenerator;

    public ReadingService(ReadingRepository readingRepository, ReadingGenerator readingGenerator) {
        this.readingRepository = readingRepository;
        this.readingGenerator = readingGenerator;
    }

    public Reading getReading(Long entityId, String entityType) {
        return this.readingRepository.findByEntityIdAndEntityType(entityId, entityType).orElse(null);
    }

    public Reading createReading(IsAbleToCreateReadings entity) {
        Long entityId = entity.getId();
        String entityType = entity.getType();

        Reading lastReading = this.getReading(entityId, entityType);

        Reading newReading = new Reading(entityId,
                entityType,
                lastReading == null ? 0L : this.readingGenerator.generate(lastReading.getConsumption()),
                LocalDateTime.now()
        );

        return this.readingRepository.save(newReading);
    }
}
