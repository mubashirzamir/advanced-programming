package com.smart_cities.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ConsumptionGenerator {
    private int currentReading = 0;

    private final Random random = new Random();

    private final NotifyProvider notifyProvider;

    @Autowired
    public ConsumptionGenerator(NotifyProvider notifyProvider) {
        this.notifyProvider = notifyProvider;
    }

    public int getCurrentReading() {
        return this.currentReading;
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
    }

    @Scheduled(fixedRate = 5000)
    public void generate() {
        this.setCurrentReading(this.random.nextInt(this.currentReading, this.currentReading + 100));
        this.notifyProvider.notify(this.getCurrentReading());
    }
}
