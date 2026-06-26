package com.touroperator.domain.port.out;

import com.touroperator.domain.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourRepository {

    Tour save(Tour tour);

    Optional<Tour> findById(Long id);

    List<Tour> findAll();

    void deleteById(Long id);
}