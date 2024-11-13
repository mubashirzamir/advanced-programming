package com.smart_cities.city.scheduler;

import com.smart_cities.city.service.ConsumptionAggregator;
import com.smart_cities.city.service.ProviderRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProviderPoller {
    private final ProviderRequester providerRequester;
    private final ConsumptionAggregator consumptionAggregator;
    private LocalDateTime consumptionPeriodStart = LocalDateTime.of(2024, 1, 1, 0, 0);
    private LocalDateTime consumptionPeriodEnd = LocalDateTime.of(2024, 1, 1, 0, 0);

    @Autowired
    public ProviderPoller(ProviderRequester providerRequester, ConsumptionAggregator consumptionAggregator) {
        this.providerRequester = providerRequester;
        this.consumptionAggregator = consumptionAggregator;
    }

    public LocalDateTime getConsumptionPeriodStart() {
        return this.consumptionPeriodStart;
    }

    public void setConsumptionPeriodStart(LocalDateTime consumptionPeriodStart) {
        this.consumptionPeriodStart = consumptionPeriodStart;
    }

    public LocalDateTime getConsumptionPeriodEnd() {
        return this.consumptionPeriodEnd;
    }

    public void setConsumptionPeriodEnd(LocalDateTime consumptionPeriodEnd) {
        this.consumptionPeriodEnd = consumptionPeriodEnd;
    }

    @Scheduled(fixedRate = 10000)
    public void pollAndAggregate() {
        this.setConsumptionPeriod();
        providerRequester.request(1L, this.getConsumptionPeriodStart(), this.getConsumptionPeriodEnd())
                .thenAccept(consumptions -> this.consumptionAggregator.aggregate(
                        consumptions,
                        1L,
                        this.getConsumptionPeriodStart(),
                        this.getConsumptionPeriodEnd())
                );
        providerRequester.request(2L, this.getConsumptionPeriodStart(), this.getConsumptionPeriodEnd())
                .thenAccept(consumptions -> this.consumptionAggregator.aggregate(
                        consumptions,
                        2L,
                        this.getConsumptionPeriodStart(),
                        this.getConsumptionPeriodEnd())
                );
        providerRequester.request(3L, this.getConsumptionPeriodStart(), this.getConsumptionPeriodEnd())
                .thenAccept(consumptions -> this.consumptionAggregator.aggregate(
                        consumptions,
                        3L,
                        this.getConsumptionPeriodStart(),
                        this.getConsumptionPeriodEnd())
                );
    }

    public void setConsumptionPeriod() {
        this.setConsumptionPeriodStart(this.getConsumptionPeriodEnd());
        this.setConsumptionPeriodEnd(LocalDateTime.now());
    }
}
