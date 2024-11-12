package com.smart_cities.provider.service;

import com.smart_cities.provider.model.Consumption;
import com.smart_cities.provider.specification.ConsumptionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionService {
    private ConsumptionService consumptionRepository;

    @Autowired
    public ConsumptionService(ConsumptionService consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public List<Consumption> getAllConsumptions(ConsumptionFilter consumptionFilter) {
        return this.consumptionRepository.getAllConsumptions(consumptionFilter);
    }
}
