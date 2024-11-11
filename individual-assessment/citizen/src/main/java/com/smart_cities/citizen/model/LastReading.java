package com.smart_cities.citizen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "last_readings")
public class LastReading {

    @Id
    private Long id;

    @Column
    private Long entityId;

    @Column
    private String entityType;

    @Column
    private Long lastReading;

    public LastReading() {
    }

    public LastReading(Long entityId, String entityType, Long lastReading) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.lastReading = lastReading;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getLastReading() {
        return lastReading;
    }

    public void setLastReading(Long lastReading) {
        this.lastReading = lastReading;
    }
}
