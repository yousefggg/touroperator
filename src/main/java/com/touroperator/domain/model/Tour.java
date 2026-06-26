package com.touroperator.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tour {

    private Long id;
    private String tourName;
    private String tourDescription;
    private BigDecimal tourPrice;
    private String duration;
    private LocalDate dateTour;
    private boolean active;

    public Tour(Long id,
                String tourName,
                String tourDescription,
                BigDecimal tourPrice,
                String duration,
                LocalDate dateTour,
                boolean active) {

        this.id = id;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourPrice = tourPrice;
        this.duration = duration;
        this.dateTour = dateTour;
        this.active = active;
    }

    public boolean isAvailable() {
        return active && dateTour.isAfter(LocalDate.now());
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isExpensive() {
        return tourPrice.compareTo(new BigDecimal("1000")) > 0;
    }

    public void applyDiscount(int percentDiscount) {
        if (percentDiscount <= 0 || percentDiscount >= 100) {
            throw new IllegalArgumentException("Скидка должна быть от 1 до 99 процентов");
        }

        BigDecimal multiplier = BigDecimal.valueOf(100 - percentDiscount)
                .divide(BigDecimal.valueOf(100));

        this.tourPrice = tourPrice.multiply(multiplier);
    }

    public boolean isExpired() {
        return dateTour.isBefore(LocalDate.now());
    }

    public void updateDescription(String newDescription) {
        if (newDescription == null || newDescription.isBlank()) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }
        this.tourDescription = newDescription;
    }

    public void updatePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        this.tourPrice = newPrice;
    }

    public Long getId() { return id; }
    public String getTourName() { return tourName; }
    public String getTourDescription() { return tourDescription; }
    public BigDecimal getTourPrice() { return tourPrice; }
    public String getDuration() { return duration; }
    public LocalDate getDateTour() { return dateTour; }
    public boolean isActive() { return active; }
}