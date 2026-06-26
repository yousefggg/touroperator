package com.touroperator.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    private Long id;
    private Long userId;
    private Long tourId;
    private Status status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;

    public Order(Long id, Long userId, Long tourId, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.tourId = tourId;
        this.totalPrice = totalPrice;
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void confirm() {
        if (status != Status.PENDING) {
            throw new IllegalStateException("Можно подтвердить только заказ в статусе PENDING");
        }
        this.status = Status.CONFIRMED;
    }

    public void cancel() {
        if (status == Status.COMPLETED) {
            throw new IllegalStateException("Нельзя отменить завершённый заказ");
        }
        this.status = Status.CANCELLED;
    }

    public void complete() {
        if (status != Status.CONFIRMED) {
            throw new IllegalStateException("Можно завершить только подтверждённый заказ");
        }
        this.status = Status.COMPLETED;
    }

    public boolean isCancellable() {
        return status == Status.PENDING || status == Status.CONFIRMED;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getTourId() { return tourId; }
    public Status getStatus() { return status; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}