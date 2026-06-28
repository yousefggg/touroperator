package com.touroperator.application.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourFilter {
    private String tourName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
