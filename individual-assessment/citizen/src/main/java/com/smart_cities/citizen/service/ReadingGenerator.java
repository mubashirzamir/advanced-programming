package com.smart_cities.citizen.service;

import com.smart_cities.citizen.contracts.GenerateBehavior;

import java.util.Random;

public class ReadingGenerator implements GenerateBehavior {
    private final Random random = new Random();

    private int currentNumber = 0;

    public int getCurrentNumber() {
        return this.currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int generate() {
        this.setCurrentNumber(this.random.nextInt(this.currentNumber, this.currentNumber + 100));

        return this.getCurrentNumber();
    }
}
