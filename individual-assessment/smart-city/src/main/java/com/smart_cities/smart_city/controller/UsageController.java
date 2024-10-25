package com.smart_cities.smart_city.controller;

import com.smart_cities.smart_city.model.Usage;
import com.smart_cities.smart_city.repository.UsageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsageController {
    private final UsageRepository usageRepository;

    public UsageController(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    @GetMapping("/usages")
    public List<Usage> getAllUsages() {
        return this.usageRepository.findAll();
    }

    @GetMapping("/usages/{id}")
    public ResponseEntity<Usage> getUsageById(@PathVariable Long id) {
        return this.usageRepository.findById(id)
                .map(consumption -> new ResponseEntity<>(consumption, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/usages")
    public ResponseEntity<Usage> createUsage(@RequestBody Usage newUsage) {
        Usage consumption = this.usageRepository.save(newUsage);
        return new ResponseEntity<>(consumption, HttpStatus.CREATED);
    }

    @PutMapping("/usages/{id}")
    public ResponseEntity<Void> updateUsage(@PathVariable Long id,
                                                             @RequestBody Usage updatedUsage) {
        return this.usageRepository.findById(id)
                .map(consumption -> {
                    consumption.setProviderId(updatedUsage.getProviderId());
                    consumption.setConsumption(updatedUsage.getConsumption());
                    consumption.setCitizenId(updatedUsage.getCitizenId());
                    consumption.setMeterId(updatedUsage.getMeterId());
                    this.usageRepository.save(consumption);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/usages/{id}")
    public ResponseEntity<Void> deleteUsage(@PathVariable Long id) {
        return this.usageRepository.findById(id)
                .map(consumption -> {
                    this.usageRepository.delete(consumption);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
