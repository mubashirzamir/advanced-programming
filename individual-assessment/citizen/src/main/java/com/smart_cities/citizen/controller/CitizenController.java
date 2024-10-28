package com.smart_cities.citizen.controller;

import com.smart_cities.citizen.model.Citizen;
import com.smart_cities.citizen.repository.CitizenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitizenController {
    private final CitizenRepository citizenRepository;

    public CitizenController(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @GetMapping("/citizens")
    public List<Citizen> getAllCitizens() {
        return this.citizenRepository.findAll();
    }

    @GetMapping("/citizens/{id}")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable Long id) {
        return this.citizenRepository.findById(id)
                .map(citizen -> new ResponseEntity<>(citizen, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/citizens")
    public ResponseEntity<Citizen> createCitizen(@RequestBody Citizen newCitizen) {
        Citizen citizen = this.citizenRepository.save(newCitizen);
        return new ResponseEntity<>(citizen, HttpStatus.CREATED);
    }

    @PutMapping("/citizens/{id}")
    public ResponseEntity<Void> updateCitizen(@PathVariable Long id,
                                              @RequestBody Citizen updatedCitizen) {
        return this.citizenRepository.findById(id)
                .map(citizen -> {
                    citizen.setName(updatedCitizen.getName());
                    citizen.setAddress(updatedCitizen.getAddress());
                    this.citizenRepository.save(citizen);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/citizens/{id}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable Long id) {
        return this.citizenRepository.findById(id)
                .map(citizen -> {
                    this.citizenRepository.delete(citizen);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
