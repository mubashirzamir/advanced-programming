package com.smart_cities.citizen.model;

import com.smart_cities.citizen.contracts.GenerateBehavior;
import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.service.ReadingGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "meters")
public class Meter implements GeneratesReadings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long providerId;

    @Transient
    GenerateBehavior generateBehavior;

    public Meter() {
        this.generateBehavior = new ReadingGenerator();
    }

    public Meter(Long providerId) {
        this.providerId = providerId;
        this.generateBehavior = new ReadingGenerator();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProviderId() {
        return this.providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getType() {
        return "smartMeter";
    }

    public int generateReading() {
        return this.generateBehavior.generate();
    }
}
