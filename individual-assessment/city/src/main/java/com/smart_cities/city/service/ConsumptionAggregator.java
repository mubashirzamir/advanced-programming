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

    /**
     * Aggregates the consumption data.
     * The aggregation is done by calculating the total and average consumption.
     * The aggregated data is then saved to the database.
     */
    public final void aggregate(
            List<Consumption> consumptions,
            Long providerId,
            LocalDateTime periodStart,
            LocalDateTime periodEnd
    ) {
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
        aggregatedConsumption.setConsumptionPeriodStart(periodStart);
        aggregatedConsumption.setConsumptionPeriodEnd(periodEnd);

        this.aggregatedConsumptionRepository.save(aggregatedConsumption);
    }
}
