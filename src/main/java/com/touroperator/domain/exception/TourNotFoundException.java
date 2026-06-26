package com.touroperator.domain.exception;

public class TourNotFoundException extends DomainException {
    public TourNotFoundException(Long id) {
        super("Tour not found with id: " + id);
    }
}