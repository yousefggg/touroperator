package com.touroperator.domain.port.in;

import com.touroperator.domain.model.Tour;
import com.touroperator.application.dto.command.CreateTourCommand;
import com.touroperator.application.dto.command.UpdateTourCommand;

import java.util.List;

public interface TourUseCase {

    Tour createTour(CreateTourCommand command);

    Tour getTourById(Long id);

    List<Tour> getAllTours();

    Tour updateTour(UpdateTourCommand command);

    void deleteTourById(Long id);
}