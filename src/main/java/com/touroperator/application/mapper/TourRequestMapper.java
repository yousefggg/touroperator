package com.touroperator.application.mapper;

import com.touroperator.application.dto.command.CreateTourCommand;
import com.touroperator.application.dto.command.UpdateTourCommand;
import com.touroperator.application.dto.request.CreateTourRequest;
import com.touroperator.application.dto.request.UpdateTourRequest;

public class TourRequestMapper {

    public static CreateTourCommand toCommand(CreateTourRequest request) {
        return new CreateTourCommand(
                request.getTourName(),
                request.getTourDescription(),
                request.getTourPrice(),
                request.getDuration(),
                request.getDateTour()
        );
    }

    public static UpdateTourCommand toCommand(Long id, UpdateTourRequest request) {
        return new UpdateTourCommand(
                id,
                request.getTourName(),
                request.getTourDescription(),
                request.getTourPrice(),
                request.getDuration(),
                request.getDateTour()
        );
    }
}
