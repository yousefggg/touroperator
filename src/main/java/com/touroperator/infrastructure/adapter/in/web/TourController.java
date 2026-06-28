package com.touroperator.infrastructure.adapter.in.web;

import com.touroperator.application.dto.command.CreateTourCommand;
import com.touroperator.application.dto.command.UpdateTourCommand;
import com.touroperator.application.dto.request.CreateTourRequest;
import com.touroperator.application.dto.request.UpdateTourRequest;
import com.touroperator.application.dto.response.PageResponse;
import com.touroperator.application.dto.response.TourResponse;
import com.touroperator.application.mapper.PageMapper;
import com.touroperator.application.mapper.TourRequestMapper;
import com.touroperator.application.mapper.TourResponseMapper;
import com.touroperator.domain.model.Tour;
import com.touroperator.domain.port.in.TourUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/tours")
@RequiredArgsConstructor
public class TourController {
    private final TourUseCase  tourUseCase;

    @GetMapping
    public ResponseEntity<PageResponse<TourResponse>> getAllTours(Pageable pageable) {

        return ResponseEntity.ok(
                PageMapper.toResponse(
                        tourUseCase.getAllTours(pageable)
                                .map(TourResponseMapper::toResponse)
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<TourResponse> getTourById(@PathVariable Long id) {
        return ResponseEntity.ok(
                TourResponseMapper.toResponse(
                        tourUseCase.getTourById(id))
        );
    }
    @PostMapping
    public ResponseEntity<TourResponse> createTour(
            @Valid @RequestBody CreateTourRequest request
    ) {

        CreateTourCommand command =
                TourRequestMapper.toCommand(request);

        Tour createdTour =
                tourUseCase.createTour(command);

        TourResponse response =
                TourResponseMapper.toResponse(createdTour);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TourResponse> updateTour(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTourRequest request
    ) {
        UpdateTourCommand command =
                TourRequestMapper.toCommand(id, request);

        Tour updated = tourUseCase.updateTour(command);

        TourResponse response =
                TourResponseMapper.toResponse(updated);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TourResponse> deleteTourById(@PathVariable Long id) {
        tourUseCase.deleteTourById(id);
        return ResponseEntity.noContent().build();

    }
}
