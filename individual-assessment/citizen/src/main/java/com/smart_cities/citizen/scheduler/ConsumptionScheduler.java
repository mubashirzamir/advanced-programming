package com.smart_cities.citizen.scheduler;

import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.entity.Consumption;
import com.smart_cities.citizen.service.NotifyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsumptionScheduler {
    private final NotifyProvider notifyProvider;

    private final GeneratesReadings generatesReadings;

    @Autowired
    public ConsumptionScheduler(GeneratesReadings generatesReadings, NotifyProvider notifyProvider) {
        this.generatesReadings = generatesReadings;
        this.notifyProvider = notifyProvider;
    }

    @Scheduled(fixedRate = 5000)
    public void generateAndNotify() {
        this.notifyProvider.notify(new Consumption(
                this.generatesReadings.getId(),
                this.generatesReadings.getType(),
                this.generatesReadings.generateReading(),
                LocalDateTime.now()
        ).toPostPayload());
    }
}
