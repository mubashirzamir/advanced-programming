package com.smart_cities.provider.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
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

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Consumption() {

    }

    public Consumption(Long meterId, Long citizenId, int consumption) {
        this.citizenId = citizenId;
        this.meterId = meterId;
        this.consumption = consumption;
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

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
}
