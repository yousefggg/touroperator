package com.touroperator.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTourCommand {

    private String tourName;
    private String tourDescription;
    private BigDecimal tourPrice;
    private String duration;
    private LocalDate dateTour;
}
