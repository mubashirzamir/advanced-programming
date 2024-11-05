package com.smart_cities.provider.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smart_cities.provider.entity.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "consumptions")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long citizenId;

    @Column()
    private Long smartMeterId;

    @Column(nullable = false)
    @PositiveOrZero
    private int consumption;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Transient
    private Provider provider;

    public Consumption() {

    }

    public Consumption(Long citizenId, Long smartMeterId, int consumption, LocalDateTime generatedAt) {
        this.citizenId = citizenId;
        this.smartMeterId = smartMeterId;
        this.consumption = consumption;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public Long getCitizenId() {
        return this.citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getSmartMeterId() {
        return this.smartMeterId;
    }

    public void setSmartMeterId(Long smartMeterId) {
        this.smartMeterId = smartMeterId;
    }

    public int getConsumption() {
        return this.consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public LocalDateTime getGeneratedAt() {
        return this.generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    @AssertTrue(message = "Either citizen ID or meter ID must be provided.")
    private boolean isCitizenIdCorrect() {
        System.out.println(this.toString());
        if (this.citizenId == null && this.smartMeterId == null) {
            return false;
        }

        if (this.citizenId != null && this.smartMeterId != null) {
            return false;
        }

        return true;
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

    public Map<String, Object> toPostPayload() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.getId());
        map.put("provider_id", this.getProvider().getId());
        map.put("citizen_id", this.getCitizenId());
        map.put("smart_meter_id", this.getSmartMeterId());
        map.put("consumption", this.getConsumption());
        map.put("generatedAt", this.getGeneratedAt());

        return map;
    }
}
