package com.smart_cities.citizen.scheduler;

import com.smart_cities.citizen.service.NotifyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionScheduler {
    private final NotifyProvider notifyProvider;

    @Autowired
    public ConsumptionScheduler(NotifyProvider notifyProvider) {
        this.notifyProvider = notifyProvider;
    }

    @Scheduled(fixedRate = 5000)
    public void generateAndNotify() {

    }
}
