package com.touroperator.infrastructure.adapter.out.persistence.mapper;

import com.touroperator.domain.model.Tour;
import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TourMapperTest {

    @Test
    void should_map_entity_to_domain_correctly() {

        TourEntity entity = new TourEntity();
        entity.setId(1L);
        entity.setTourName("Paris Tour");
        entity.setTourDescription("Beautiful trip to Paris");
        entity.setTourPrice(new BigDecimal("1200"));
        entity.setDuration("7");
        entity.setDateTour(LocalDate.of(2026, 1, 10));
        entity.setActive(true);

        Tour actual = TourMapper.toDomain(entity);

        assertEquals(1L, actual.getId());
        assertEquals("Paris Tour", actual.getTourName());
        assertEquals("Beautiful trip to Paris", actual.getTourDescription());
        assertEquals(new BigDecimal("1200"), actual.getTourPrice());
        assertEquals("7", actual.getDuration());
        assertEquals(LocalDate.of(2026, 1, 10), actual.getDateTour());
        assertTrue(actual.isActive());
    }

    @Test
    void should_return_null_when_entity_is_null() {

        Tour actual = TourMapper.toDomain(null);

        assertNull(actual);
    }

    @Test
    void should_return_null_when_domain_is_null() {

        TourEntity actual = TourMapper.toEntityForCreate(null);

        assertNull(actual);
    }

    @Test
    void should_map_domain_to_entity_for_create() {

        Tour domain = new Tour(
                1L,
                "Rome Tour",
                "Trip to Rome",
                new BigDecimal("900"),
                "5",
                LocalDate.of(2026, 2, 15),
                false
        );

        TourEntity actual = TourMapper.toEntityForCreate(domain);

        assertEquals("Rome Tour", actual.getTourName());
        assertEquals("Trip to Rome", actual.getTourDescription());
        assertEquals(new BigDecimal("900"), actual.getTourPrice());
        assertEquals("5", actual.getDuration());
        assertEquals(LocalDate.of(2026, 2, 15), actual.getDateTour());
        assertFalse(actual.isActive());

        // id не должен устанавливаться при create
        assertNull(actual.getId());
    }

    @Test
    void should_update_existing_entity_from_domain() {

        Tour domain = new Tour(
                1L,
                "Berlin Tour",
                "Trip to Berlin",
                new BigDecimal("800"),
                "4",
                LocalDate.of(2026, 3, 20),
                true
        );

        TourEntity entity = new TourEntity();

        TourMapper.updateEntity(domain, entity);

        assertEquals("Berlin Tour", entity.getTourName());
        assertEquals("Trip to Berlin", entity.getTourDescription());
        assertEquals(new BigDecimal("800"), entity.getTourPrice());
        assertEquals("4", entity.getDuration());
        assertEquals(LocalDate.of(2026, 3, 20), entity.getDateTour());
        assertTrue(entity.isActive());
    }

    @Test
    void should_not_update_when_domain_is_null() {

        TourEntity entity = new TourEntity();
        entity.setTourName("Old Name");
        entity.setTourDescription("Old Desc");

        TourMapper.updateEntity(null, entity);

        assertEquals("Old Name", entity.getTourName());
        assertEquals("Old Desc", entity.getTourDescription());
    }

    @Test
    void should_not_update_when_entity_is_null() {

        Tour domain = new Tour(
                1L,
                "Madrid Tour",
                "Trip to Madrid",
                new BigDecimal("700"),
                "3",
                LocalDate.of(2026, 4, 10),
                true
        );

        TourMapper.updateEntity(domain, null);

        assertTrue(true);
    }
}