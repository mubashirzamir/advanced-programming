package com.smart_cities.citizen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class ConsumptionPayload {
    private Long citizenId;
    private Long meterId;
    private int consumption;
    private LocalDateTime timestamp;

    public ConsumptionPayload(Long citizenId, Long meterId, int consumption, LocalDateTime timestamp) {
        this.citizenId = citizenId;
        this.meterId = meterId;
        this.consumption = consumption;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
