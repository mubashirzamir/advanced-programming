package com.formative_feedback_01.shipment_management.repository;

import com.formative_feedback_01.shipment_management.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
