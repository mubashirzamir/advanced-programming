package com.smart_cities.provider.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

@Entity
@Table(name = "consumptions")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long citizenId;

    @Column()
    private Long meterId;

    @Column(nullable = false)
    @PositiveOrZero
    private int consumption;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Transient
    private Long providerId;

    public Consumption() {

    }

    public Consumption(Long meterId, Long citizenId, int consumption, LocalDateTime generatedAt) {
        this.citizenId = citizenId;
        this.meterId = meterId;
        this.consumption = consumption;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public Long getMeterId() {
        return this.meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Long getCitizenId() {
        return this.citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public int getConsumption() {
        return this.consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public Long getProviderId() {
        return this.providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public LocalDateTime getGeneratedAt() {
        return this.generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    @AssertTrue(message = "Either citizen ID or meter ID must be provided.")
    private boolean isCitizenIdCorrect() {
        if (this.citizenId == null && this.meterId == null) {
            return false;
        }

        if (this.citizenId != null && this.meterId != null) {
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
}
