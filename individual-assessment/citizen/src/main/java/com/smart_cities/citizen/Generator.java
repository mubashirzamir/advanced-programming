package com.smart_cities.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class Generator {

    private final RestTemplate restTemplate;

    private static final String TARGET_MICROSERVICE_URL = "http://localhost:8082/consumptions";

    @Autowired
    public Generator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public String generate() {
        System.out.println("generating");
        ConsumptionPayload consumptionPayload = new ConsumptionPayload(1L, null, 100, LocalDateTime.now());
        return this.restTemplate.postForObject(TARGET_MICROSERVICE_URL, consumptionPayload, String.class);
    }
}
