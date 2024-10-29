package com.smart_cities.citizen;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReadingGeneratorService {
    @Scheduled(fixedRate = 1000)
    public void generateReadings() {

    }
}
