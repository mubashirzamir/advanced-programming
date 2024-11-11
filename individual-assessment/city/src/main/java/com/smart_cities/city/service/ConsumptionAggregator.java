package com.smart_cities.city.service;

import com.smart_cities.city.dto.Consumption;
import com.smart_cities.city.model.AggregatedConsumption;
import com.smart_cities.city.repository.AggregatedConsumptionRepository;
import org.springframework.stereotype.Service;

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

        this.aggregatedConsumptionRepository.save(aggregatedConsumption);
    }
}
