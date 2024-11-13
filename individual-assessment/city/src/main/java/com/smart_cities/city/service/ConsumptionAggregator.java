package com.smart_cities.city.service;

import com.smart_cities.city.dto.Consumption;
import com.smart_cities.city.model.AggregatedConsumption;
import com.smart_cities.city.repository.AggregatedConsumptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionAggregator {
    private final AggregatedConsumptionRepository aggregatedConsumptionRepository;

    public ConsumptionAggregator(AggregatedConsumptionRepository aggregatedConsumptionRepository) {
        this.aggregatedConsumptionRepository = aggregatedConsumptionRepository;
    }

    public final void aggregate(List<Consumption> consumptions, Long providerId) {
        Long totalConsumption = 0L;
        Long averageConsumption = 0L;

        for (Consumption consumption : consumptions) {
            totalConsumption += consumption.getConsumption();
        }

        averageConsumption = totalConsumption / consumptions.size();

        AggregatedConsumption aggregatedConsumption = new AggregatedConsumption();
        aggregatedConsumption.setProviderId(providerId);
        aggregatedConsumption.setTotalConsumption(totalConsumption);
        aggregatedConsumption.setAverageConsumption(averageConsumption);
        aggregatedConsumption.setConsumptionPeriodStart(this.deriveConsumptionPeriodStart(providerId));
//        aggregatedConsumption.setConsumptionPeriodEnd(periodEnd);

        this.aggregatedConsumptionRepository.save(aggregatedConsumption);
    }

    public LocalDateTime deriveConsumptionPeriodStart(Long providerId) {
        return this.aggregatedConsumptionRepository.findFirstByProviderIdOrderByConsumptionPeriodEnd(providerId)
                .map(AggregatedConsumption::getConsumptionPeriodEnd)
                .orElse(LocalDateTime.of(2024, 1, 1, 0, 0, 0));
    }
}
