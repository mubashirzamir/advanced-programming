package com.smart_cities.citizen.service;

import com.smart_cities.citizen.contracts.GenerateBehavior;

import java.util.Random;

public class ConsumptionGenerator implements GenerateBehavior {
    private final Random random = new Random();

    public int generate(int lastGeneratedNumber) {
        return this.random.nextInt(lastGeneratedNumber, lastGeneratedNumber + 100);
    }
}
