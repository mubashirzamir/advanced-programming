package com.smart_cities.citizen.model;

import com.smart_cities.citizen.contracts.GenerateBehavior;
import com.smart_cities.citizen.service.ReadingGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "citizens")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long providerId;

    @Transient
    GenerateBehavior generateBehavior;

    public Citizen() {
        this.generateBehavior = new ReadingGenerator();
    }

    public Citizen(Long providerId) {
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
        return "citizen";
    }

    public int generateReading() {
        return this.generateBehavior.generate();
    }
}
