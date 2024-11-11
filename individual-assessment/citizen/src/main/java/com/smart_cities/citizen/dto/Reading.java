package com.smart_cities.citizen.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Reading {
    private Long id;
    private String type;
    private Long consumption;
    private LocalDateTime generatedAt;

    public Reading(Long id, String type, Long consumption, LocalDateTime generatedAt) {
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

    public Long getConsumption() {
        return this.consumption;
    }

    public void setConsumption(Long consumption) {
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
