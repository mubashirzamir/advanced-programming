package com.smart_cities.provider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CityNotifier {
    private static final String CITY_URL = "http://localhost:8080/consumptions";

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CityNotifier.class);

    @Autowired
    public CityNotifier(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void notify(Map<String, Object> consumption) {
        CityNotifier.logger.info("Received from citizen: " + consumption);
        Object response = this.restTemplate.postForObject(CITY_URL, consumption, String.class);
        CityNotifier.logger.info("City Response: " + response.toString());
    }
}
