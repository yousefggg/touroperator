package com.touroperator.infrastructure.adapter.out.persistence.specification;

import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TourSpecification {

    public static Specification<TourEntity> tourNameContains(String tourName) {
        return (root, query, cb) -> {
            if (tourName == null || tourName.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("tourName")),
                    "%" + tourName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<TourEntity> priceGreaterOrEqual(BigDecimal minPrice) {
        return (root, query, cb) -> {
            if (minPrice == null) {
                return cb.conjunction();
            }

            return cb.greaterThanOrEqualTo(root.get("tourPrice"), minPrice);
        };
    }

    public static Specification<TourEntity> priceLessOrEqual(BigDecimal maxPrice) {
        return (root, query, cb) -> {
            if (maxPrice == null) {
                return cb.conjunction();
            }

            return cb.lessThanOrEqualTo(root.get("tourPrice"), maxPrice);
        };
    }
}