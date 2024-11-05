package com.smart_cities.citizen.entity;

import com.smart_cities.citizen.contracts.GenerateBehavior;
import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.service.ConsumptionGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmartMeter implements GeneratesReadings {
    @Value("${id}")
    private Long id;

    GenerateBehavior generateBehavior;

    public SmartMeter() {
        this.generateBehavior = new ConsumptionGenerator();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return "smart_meter";
    }

    public int generateReading() {
        return generateBehavior.generate();
    }
}
