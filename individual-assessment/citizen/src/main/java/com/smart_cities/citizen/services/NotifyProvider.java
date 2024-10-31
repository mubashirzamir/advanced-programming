package com.smart_cities.citizen.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class NotifyProvider {
    @Value("${provider_name}")
    private static String providerName;

    @Value("${citizen_id}")
    private Long citizenId;

    @Value("${meter_id}")
    private Long meterId;

    private static final String PROVIDER_URL = "http://localhost:8082/" + "/consumptions";

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(NotifyProvider.class);

    @Autowired
    public NotifyProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public Long getMeterId() {
        return meterId;
    }

    @Async
    public void notify(int consumption) {
        ConsumptionPayload consumptionPayload = new ConsumptionPayload(
                this.getCitizenId(),
                this.getMeterId(),
                consumption,
                LocalDateTime.now()
        );

        NotifyProvider.logger.info("Generated: " + consumptionPayload);
        Object response = this.restTemplate.postForObject(PROVIDER_URL, consumptionPayload, String.class);
        NotifyProvider.logger.info("Provider Response: " + response.toString());
    }
}
