package com.smart_cities.citizen.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ReadingGenerator {
    private final Random random = new Random();

    public Long generate(Long lastReading) {
        return this.random.nextLong(lastReading, lastReading + 10);
    }
}
