package com.smart_cities.citizen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Reading {
    private Long id;
    private String type;
    private int reading;
    private LocalDateTime generatedAt;

    public Reading(Long id, String type, int reading, LocalDateTime generatedAt) {
        this.id = id;
        this.type = type;
        this.reading = reading;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReading() {
        return this.reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Map<String, Object> toPostPayload() {
        Map<String, Object> map = new HashMap<>();

        map.put(this.getType() + "Id", this.getId());
        map.put("consumption", this.getReading());
        map.put("generatedAt", this.getGeneratedAt());

        return map;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
