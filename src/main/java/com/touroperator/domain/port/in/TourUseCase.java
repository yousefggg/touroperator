package com.touroperator.domain.port.in;

import com.touroperator.domain.model.Tour;
import com.touroperator.application.dto.command.CreateTourCommand;
import com.touroperator.application.dto.command.UpdateTourCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourUseCase {

    Tour createTour(CreateTourCommand command);

    Tour getTourById(Long id);

    Page<Tour> getAllTours(Pageable pageable);

    Tour updateTour(UpdateTourCommand command);

    void deleteTourById(Long id);
}