package com.touroperator.application.service;

import com.touroperator.application.dto.command.CreateTourCommand;
import com.touroperator.application.dto.command.UpdateTourCommand;
import com.touroperator.domain.exception.TourNotFoundException;
import com.touroperator.domain.model.Tour;
import com.touroperator.domain.port.in.TourUseCase;
import com.touroperator.domain.port.out.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

        log.info("Creating new tour with name='{}'", command.getTourName());

        Tour tour = new Tour(
                null,
                command.getTourName(),
                command.getTourDescription(),
                command.getTourPrice(),
                command.getDuration(),
                command.getDateTour(),
                true
        );

        Tour createdTour = tourRepository.save(tour);

        log.info("Tour created successfully. id={}", createdTour.getId());

        return createdTour;
    }

    @Override
    public Tour getTourById(Long id) {

        log.info("Getting tour by id={}", id);

        return tourRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tour not found. id={}", id);
                    return new TourNotFoundException(id);
                });
    }

    @Override
    public Page<Tour> getAllTours(Pageable pageable) {
        log.info("Getting all tours with pagination");
        return tourRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Tour updateTour(UpdateTourCommand command) {

        log.info("Updating tour. id={}", command.getId());

        Tour existingTour = tourRepository.findById(command.getId())
                .orElseThrow(() -> {
                    log.warn("Tour not found. id={}", command.getId());
                    return new TourNotFoundException(command.getId());
                });

        Tour updatedTour = new Tour(
                existingTour.getId(),
                command.getTourName(),
                command.getTourDescription(),
                command.getTourPrice(),
                command.getDuration(),
                command.getDateTour(),
                existingTour.isActive()
        );

        Tour savedTour = tourRepository.save(updatedTour);

        log.info("Tour updated successfully. id={}", savedTour.getId());

        return savedTour;
    }

    @Override
    @Transactional
    public void deleteTourById(Long id) {

        log.info("Deleting tour. id={}", id);

        Tour existingTour = tourRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tour not found. id={}", id);
                    return new TourNotFoundException(id);
                });

        tourRepository.deleteById(existingTour.getId());

        log.info("Tour deleted successfully. id={}", id);
    }
}