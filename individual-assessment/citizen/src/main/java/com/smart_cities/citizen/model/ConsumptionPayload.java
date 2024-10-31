package com.smart_cities.citizen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class ConsumptionPayload {
    private Long citizenId;
    private Long meterId;
    private int consumption;
    private LocalDateTime generatedAt;

    public ConsumptionPayload(Long citizenId, Long meterId, int consumption, LocalDateTime generatedAt) {
        this.citizenId = citizenId;
        this.meterId = meterId;
        this.consumption = consumption;
        this.generatedAt = generatedAt;
    }

    public Long getCitizenId() {
        return this.citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getMeterId() {
        return this.meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public int getConsumption() {
        return consumption;
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
