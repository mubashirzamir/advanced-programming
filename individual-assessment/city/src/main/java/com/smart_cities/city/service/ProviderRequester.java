package com.smart_cities.city.service;

import com.smart_cities.city.dto.Consumption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class ProviderRequester {
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProviderRequester.class);

    @Autowired
    public ProviderRequester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<List<Consumption>> request(Long providerId) {
        try {
            String providerUrl = "http://localhost:8080/provider" + "/" + providerId + "/consumptions";
            List<Consumption> consumptions = List.of(Objects.requireNonNull(
                    this.restTemplate.getForObject(providerUrl, Consumption[].class)
            ));
            ProviderRequester.logger.info("Provider " + providerId + " Response: " + consumptions);

            return CompletableFuture.completedFuture(consumptions);
        } catch (Exception e) {
            ProviderRequester.logger.error(e.getMessage());
        }

        return CompletableFuture.completedFuture(List.of());
    }
}
