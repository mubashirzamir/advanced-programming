package com.smart_cities.citizen.entity;

import com.smart_cities.citizen.contracts.GenerateBehavior;
import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.service.ConsumptionGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Citizen implements GeneratesReadings {
    @Value("${citizen_id}")
    private Long citizenId;

    GenerateBehavior generateBehavior;

    public Citizen() {
        this.generateBehavior = new ConsumptionGenerator();
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public int generateReading(int lastGeneratedNumber) {
        return generateBehavior.generate(lastGeneratedNumber);
    }
}
