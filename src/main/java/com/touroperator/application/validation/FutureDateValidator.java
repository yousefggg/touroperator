package com.touroperator.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Clock;
import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {

    private final Clock clock;

    public FutureDateValidator(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void initialize(FutureDate constraintAnnotation) {}

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        LocalDate today = LocalDate.now(clock);

        return value.isAfter(today);
    }
}