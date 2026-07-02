package com.touroperator.domain.exception;

public class TourNotAvailableException extends RuntimeException {
    public TourNotAvailableException(String message) {
        super(message);
    }
}