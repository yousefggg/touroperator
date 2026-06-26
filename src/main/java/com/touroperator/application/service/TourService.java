package com.touroperator.application.service;

import com.touroperator.application.dto.command.CreateTourCommand;
import com.touroperator.application.dto.command.UpdateTourCommand;
import com.touroperator.domain.exception.TourNotFoundException;
import com.touroperator.domain.model.Tour;
import com.touroperator.domain.port.in.TourUseCase;
import com.touroperator.domain.port.out.TourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TourService implements TourUseCase {

    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    @Transactional
    public Tour createTour(CreateTourCommand command) {

        Tour tour = new Tour(
                null,
                command.getTourName(),
                command.getTourDescription(),
                command.getTourPrice(),
                command.getDuration(),
                command.getDateTour(),
                true
        );

        return tourRepository.save(tour);
    }

    @Override
    public Tour getTourById(Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() ->
                        new TourNotFoundException(id));
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    @Transactional
    public Tour updateTour(UpdateTourCommand command) {

        Tour existingTour = tourRepository.findById(command.getId())
                .orElseThrow(() ->
                        new TourNotFoundException(command.getId()));

        Tour updatedTour = new Tour(
                existingTour.getId(),
                command.getTourName(),
                command.getTourDescription(),
                command.getTourPrice(),
                command.getDuration(),
                command.getDateTour(),
                existingTour.isActive()
        );

        return tourRepository.save(updatedTour);
    }

    @Override
    @Transactional
    public void deleteTourById(Long id) {

        Tour existingTour = tourRepository.findById(id)
                .orElseThrow(() ->
                        new TourNotFoundException(id));

        tourRepository.deleteById(existingTour.getId());
    }
}