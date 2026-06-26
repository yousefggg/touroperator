package com.touroperator.domain.exception;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }
}