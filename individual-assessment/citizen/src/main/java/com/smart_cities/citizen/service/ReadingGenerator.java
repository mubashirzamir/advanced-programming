package com.smart_cities.citizen.service;

import com.smart_cities.citizen.contracts.GenerateBehavior;

import java.util.Random;

public class ReadingGenerator implements GenerateBehavior {
    private final Random random = new Random();

    private Long currentNumber = 0L;

    public Long getCurrentNumber() {
        return this.currentNumber;
    }

    public void setCurrentNumber(Long currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Long generate() {
        this.setCurrentNumber(this.random.nextLong(this.currentNumber, this.currentNumber + 100));

        return this.getCurrentNumber();
    }
}
