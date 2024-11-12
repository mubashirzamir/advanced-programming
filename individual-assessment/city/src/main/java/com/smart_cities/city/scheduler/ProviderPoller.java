package com.smart_cities.city.scheduler;

import com.smart_cities.city.service.ConsumptionAggregator;
import com.smart_cities.city.service.ProviderRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProviderPoller {
    private final ProviderRequester providerRequester;
    private final ConsumptionAggregator consumptionAggregator;

    @Autowired
    public ProviderPoller(ProviderRequester providerRequester, ConsumptionAggregator consumptionAggregator) {
        this.providerRequester = providerRequester;
        this.consumptionAggregator = consumptionAggregator;
    }

    @Scheduled(fixedRate = 60000)
    public void pollAndAggregate() {
        providerRequester.request(1L)
                .thenAccept(consumptions -> this.consumptionAggregator.aggregate(consumptions, 1L));
        providerRequester.request(2L)
                .thenAccept(consumptions -> this.consumptionAggregator.aggregate(consumptions, 2L));
        providerRequester.request(3L)
                .thenAccept(consumptions -> this.consumptionAggregator.aggregate(consumptions, 3L));
    }
}
