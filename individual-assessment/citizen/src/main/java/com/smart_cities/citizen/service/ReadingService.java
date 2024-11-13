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

    public Reading generateReading(IsAbleToCreateReadings entity) {
        Long entityId = entity.getId();
        String entityType = entity.getType();

        return this.readingRepository.findByEntityIdAndEntityType(entityId, entityType)
                .map(reading -> {
                    reading.setConsumption(this.readingGenerator.generate(reading.getConsumption()));
                    reading.setGeneratedAt(LocalDateTime.now());
                    this.readingRepository.save(reading);

                    return reading;
                })
                .orElseGet(() ->
                        this.readingRepository.save(new Reading(
                                entityId,
                                entityType,
                                this.readingGenerator.generate(0L),
                                LocalDateTime.now())
                        )
                );
    }
}
