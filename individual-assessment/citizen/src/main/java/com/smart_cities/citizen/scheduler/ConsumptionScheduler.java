package com.smart_cities.citizen.scheduler;

import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.entity.Consumption;
import com.smart_cities.citizen.service.ProviderNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsumptionScheduler {
    private final ProviderNotifier ProviderNotifier;

    private final GeneratesReadings generatesReadings;

    @Autowired
    public ConsumptionScheduler(GeneratesReadings generatesReadings, ProviderNotifier ProviderNotifier) {
        this.generatesReadings = generatesReadings;
        this.ProviderNotifier = ProviderNotifier;
    }

    @Scheduled(fixedRate = 5000)
    public void generateAndNotify() {
        this.ProviderNotifier.notify(new Consumption(
                this.generatesReadings.getId(),
                this.generatesReadings.getType(),
                this.generatesReadings.generateReading(),
                LocalDateTime.now()
        ).toPostPayload());
    }
}
