package com.touroperator.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourResponse {

    private Long id;
    private String tourName;
    private String tourDescription;
    private BigDecimal tourPrice;
    private String duration;
    private LocalDate dateTour;
    private boolean active;
}