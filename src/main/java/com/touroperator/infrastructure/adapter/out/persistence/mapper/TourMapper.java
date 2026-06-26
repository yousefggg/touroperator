package com.touroperator.infrastructure.adapter.out.persistence.mapper;

import com.touroperator.domain.model.Tour;
import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;

public final class TourMapper {

    private TourMapper() {}

    public static Tour toDomain(TourEntity entity) {
        if (entity == null) return null;

        return new Tour(
                entity.getId(),
                entity.getTourName(),
                entity.getTourDescription(),
                entity.getTourPrice(),
                entity.getDuration(),
                entity.getDateTour(),
                entity.isActive()
        );
    }

    public static TourEntity toEntityForCreate(Tour domain) {
        if (domain == null) return null;

        TourEntity entity = new TourEntity();

        entity.setTourName(domain.getTourName());
        entity.setTourDescription(domain.getTourDescription());
        entity.setTourPrice(domain.getTourPrice());
        entity.setDuration(domain.getDuration());
        entity.setDateTour(domain.getDateTour());
        entity.setActive(domain.isActive());

        return entity;
    }

    public static void updateEntity(Tour domain, TourEntity entity) {
        if (domain == null || entity == null) return;

        entity.setTourName(domain.getTourName());
        entity.setTourDescription(domain.getTourDescription());
        entity.setTourPrice(domain.getTourPrice());
        entity.setDuration(domain.getDuration());
        entity.setDateTour(domain.getDateTour());
        entity.setActive(domain.isActive());
    }
}