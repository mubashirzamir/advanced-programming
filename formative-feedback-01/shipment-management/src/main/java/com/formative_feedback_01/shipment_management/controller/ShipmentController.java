package com.formative_feedback_01.shipment_management.controller;

import com.formative_feedback_01.shipment_management.model.Shipment;
import com.formative_feedback_01.shipment_management.repository.ShipmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipmentController {
    private final ShipmentRepository shipmentRepository;

    public ShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @GetMapping("/shipments")
    public List<Shipment> getAllShipments() {
        return this.shipmentRepository.findAll();
    }

    @GetMapping("/shipments/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        return this.shipmentRepository.findById(id)
                .map(shipment -> new ResponseEntity<>(shipment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/shipments")
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment newShipment) {
        Shipment shipment = this.shipmentRepository.save(newShipment);
        return new ResponseEntity<>(shipment, HttpStatus.CREATED);
    }

    @PutMapping("/shipments/{id}")
    public ResponseEntity<Void> updateShipment(@PathVariable Long id,
                                               @RequestBody Shipment updatedShipment) {
        return this.shipmentRepository.findById(id)
                .map(shipment -> {
                    shipment.setCustomerId(updatedShipment.getCustomerId());
                    shipment.setCurrentLocation(updatedShipment.getCurrentLocation());
                    shipment.setPickupAddress(updatedShipment.getPickupAddress());
                    shipment.setDeliveryAddress(updatedShipment.getDeliveryAddress());
                    shipment.setPickupAt(updatedShipment.getPickupAt());
                    shipment.setDeliveryAt(updatedShipment.getDeliveryAt());
                    this.shipmentRepository.save(shipment);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/shipments/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        return this.shipmentRepository.findById(id)
                .map(shipment -> {
                    this.shipmentRepository.delete(shipment);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
