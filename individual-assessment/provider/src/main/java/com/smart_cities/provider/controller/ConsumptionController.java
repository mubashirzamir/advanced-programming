package com.smart_cities.provider.controller;

import com.smart_cities.provider.model.Consumption;
import com.smart_cities.provider.repository.ConsumptionRepository;
import com.smart_cities.provider.service.ConsumptionService;
import com.smart_cities.provider.specification.ConsumptionFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ConsumptionController {
    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionRepository consumptionRepository, ConsumptionService consumptionService) {
        this.consumptionRepository = consumptionRepository;
        this.consumptionService = consumptionService;
    }

    @GetMapping("/consumptions")
    public List<Consumption> getAllConsumptions(
            @RequestParam(value = "consumptionPeriodStart") LocalDateTime consumptionPeriodStart,
            @RequestParam(value = "consumptionPeriodEnd") LocalDateTime consumptionPeriodEnd,
            ConsumptionFilter consumptionFilter
    ) {
        return this.consumptionService.getAllConsumptions(consumptionFilter);
    }

    @GetMapping("/consumptions/{id}")
    public ResponseEntity<Consumption> getConsumptionById(@PathVariable Long id) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> new ResponseEntity<>(consumption, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/consumptions")
    public ResponseEntity<Consumption> createConsumption(@RequestBody Consumption newConsumption) {
        return new ResponseEntity<>(this.consumptionRepository.save(newConsumption), HttpStatus.CREATED);
    }

    @PutMapping("/consumptions/{id}")
    public ResponseEntity<Void> updateConsumption(@PathVariable Long id,
                                                  @RequestBody Consumption updatedConsumption) {
        return this.consumptionRepository.findById(id)
                .map(consumption -> {
                    consumption.setEntityId(updatedConsumption.getEntityId());
                    consumption.setEntityType(updatedConsumption.getEntityType());
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
