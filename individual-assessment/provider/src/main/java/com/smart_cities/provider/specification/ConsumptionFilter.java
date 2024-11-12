package com.smart_cities.provider.specification;

import com.smart_cities.provider.model.Consumption;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsumptionFilter implements Specification<Consumption> {
    private LocalDateTime consumptionPeriodStart;
    private LocalDateTime consumptionPeriodEnd;

    public LocalDateTime getConsumptionPeriodStart() {
        return consumptionPeriodStart;
    }

    public void setConsumptionPeriodStart(LocalDateTime consumptionPeriodStart) {
        this.consumptionPeriodStart = consumptionPeriodStart;
    }

    public LocalDateTime getConsumptionPeriodEnd() {
        return consumptionPeriodEnd;
    }

    public void setConsumptionPeriodEnd(LocalDateTime consumptionPeriodEnd) {
        this.consumptionPeriodEnd = consumptionPeriodEnd;
    }

    @Override
    public Predicate toPredicate(Root<Consumption> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        // Filter by start period
        if (!ObjectUtils.isEmpty(this.consumptionPeriodStart)) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("generatedAt"), this.getConsumptionPeriodStart()));
        }

        // Filter by end period
        if (!ObjectUtils.isEmpty(this.consumptionPeriodEnd)) {
            predicates.add(cb.lessThanOrEqualTo(root.get("generatedAt"), this.getConsumptionPeriodEnd()));
        }


        return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
