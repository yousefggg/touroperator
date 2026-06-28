package com.touroperator.domain.port.out;

import com.touroperator.application.dto.filter.TourFilter;
import com.touroperator.domain.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface TourRepository {

    Tour save(Tour tour);

    Optional<Tour> findById(Long id);

    Page<Tour> findAll(TourFilter tourFilter, Pageable pageable);

    void deleteById(Long id);
}