package com.smart_cities.citizen.service;

import com.smart_cities.citizen.entity.Consumption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotifyProvider {
    private static final String PROVIDER_URL = "http://localhost:8082/" + "/consumptions";

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(NotifyProvider.class);

    @Autowired
    public NotifyProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void notify(Consumption consumption) {
        NotifyProvider.logger.info("Generated: " + consumption);
        Object response = this.restTemplate.postForObject(PROVIDER_URL, consumption, String.class);
        NotifyProvider.logger.info("Provider Response: " + response.toString());
    }
}
