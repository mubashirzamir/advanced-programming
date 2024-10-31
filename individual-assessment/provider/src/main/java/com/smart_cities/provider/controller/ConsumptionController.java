package com.smart_cities.provider.controller;

import com.smart_cities.provider.model.Consumption;
import com.smart_cities.provider.repository.ConsumptionRepository;
import com.smart_cities.provider.service.CityNotifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsumptionController {
    private final ConsumptionRepository consumptionRepository;

    private final CityNotifier cityNotifier;

    @Value("${provider_id}")
    private Long providerId;

    public ConsumptionController(ConsumptionRepository consumptionRepository, CityNotifier cityNotifier) {
        this.consumptionRepository = consumptionRepository;
        this.cityNotifier = cityNotifier;
    }

    @GetMapping("/consumptions")
    public List<Consumption> getAllConsumptions() {
        return this.consumptionRepository.findAll();
    }

    @GetMapping("/consumptions/{id}")
    public ResponseEntity<Consumption> getConsumptionById(@PathVariable Long id) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> new ResponseEntity<>(consumption, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/consumptions")
    public ResponseEntity<Consumption> createConsumption(@RequestBody Consumption newConsumption) {
        newConsumption.setProviderId(providerId);
        Consumption consumption = this.consumptionRepository.save(newConsumption);
        this.cityNotifier.notify(consumption);
        return new ResponseEntity<>(consumption, HttpStatus.CREATED);
    }

    @PutMapping("/consumptions/{id}")
    public ResponseEntity<Void> updateConsumption(@PathVariable Long id,
                                                  @RequestBody Consumption updatedConsumption) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> {
                    consumption.setCitizenId(updatedConsumption.getCitizenId());
                    consumption.setMeterId(updatedConsumption.getMeterId());
                    consumption.setConsumption(updatedConsumption.getConsumption());
                    consumption.setGeneratedAt(updatedConsumption.getGeneratedAt());
                    this.consumptionRepository.save(consumption);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/consumptions/{id}")
    public ResponseEntity<Void> deleteConsumption(@PathVariable Long id) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> {
                    this.consumptionRepository.delete(consumption);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
