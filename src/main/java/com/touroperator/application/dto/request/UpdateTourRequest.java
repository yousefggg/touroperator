package com.touroperator.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTourRequest {

    @NotNull(message = "Tour id is required")
    @Positive(message = "Tour id must be positive")
    private Long id;

    @NotBlank(message = "Tour name must not be blank")
    @Size(max = 255, message = "Tour name must not exceed 255 characters")
    private String tourName;

    @Size(max = 1000, message = "Tour description must not exceed 1000 characters")
    private String tourDescription;

    @NotNull(message = "Tour price is required")
    @DecimalMin(value = "0.01", message = "Tour price must be greater than 0")
    @Digits(integer = 8, fraction = 2,
            message = "Tour price must contain up to 8 integer digits and 2 decimal places")
    private BigDecimal tourPrice;

    @Size(max = 100, message = "Duration must not exceed 100 characters")
    private String duration;

    @NotNull(message = "Tour date is required")
    private LocalDate dateTour;

    @NotNull(message = "Active status is required")
    private Boolean active;
}