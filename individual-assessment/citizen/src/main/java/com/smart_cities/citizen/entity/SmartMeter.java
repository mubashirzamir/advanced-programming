package com.smart_cities.citizen.entity;

import com.smart_cities.citizen.contracts.GenerateBehavior;
import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.service.ConsumptionGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmartMeter implements GeneratesReadings {
    @Value("${meter_id}")
    private Long meterId;

    GenerateBehavior generateBehavior;

    public SmartMeter() {
        this.generateBehavior = new ConsumptionGenerator();
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public int generateReading(int lastGeneratedNumber) {
        return generateBehavior.generate(lastGeneratedNumber);
    }
}
