package com.smart_cities.citizen.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Consumption {
    private Long id;
    private String type;
    private int consumption;
    private LocalDateTime generatedAt;

    public Consumption(Long id, String type, int consumption, LocalDateTime generatedAt) {
        this.id = id;
        this.type = type;
        this.consumption = consumption;
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

    public int getConsumption() {
        return this.consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
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
        map.put("consumption", this.getConsumption());
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
