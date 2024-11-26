package com.smart_cities.citizen.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ReadingGenerator {
    public Long generate(Long lastReading) {
        return ThreadLocalRandom.current().nextLong(lastReading, lastReading + 5);
    }
}
