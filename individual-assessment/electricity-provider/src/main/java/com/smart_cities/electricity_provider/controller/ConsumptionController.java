package com.smart_cities.electricity_provider.controller;

import com.smart_cities.electricity_provider.model.Consumption;
import com.smart_cities.electricity_provider.repository.ConsumptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsumptionController {
    private final ConsumptionRepository consumptionRepository;

    public ConsumptionController(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
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
    public ResponseEntity<Consumption> createConsumption(@RequestBody Consumption updatedConsumption) {
        Consumption consumption = this.consumptionRepository.save(updatedConsumption);
        return new ResponseEntity<>(consumption, HttpStatus.CREATED);
    }

    @PutMapping("/consumptions/{id}")
    public ResponseEntity<Void> updateElectricityConsumption(@PathVariable Long id,
                                                             @RequestBody Consumption updatedConsumption) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> {
                    consumption.setConsumption(updatedConsumption.getConsumption());
                    consumption.setCitizenId(updatedConsumption.getCitizenId());
                    consumption.setMeterId(updatedConsumption.getMeterId());
                    this.consumptionRepository.save(consumption);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/consumptions/{id}")
    public ResponseEntity<Void> deleteElectricityConsumption(@PathVariable Long id) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> {
                    this.consumptionRepository.delete(consumption);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
