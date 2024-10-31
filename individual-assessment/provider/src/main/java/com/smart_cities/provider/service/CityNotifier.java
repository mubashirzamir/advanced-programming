package com.smart_cities.provider.service;

import com.smart_cities.provider.model.Consumption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityNotifier {
    private static final String CITY_URL = "https://httpbin.org/post";

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CityNotifier.class);

    @Autowired
    public CityNotifier(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void notify(Consumption consumption) {
        CityNotifier.logger.info("Received from citizen: " + consumption);
        Object response = this.restTemplate.postForObject(CITY_URL, consumption, String.class);
        CityNotifier.logger.info("City Response: " + response.toString());
    }
}
