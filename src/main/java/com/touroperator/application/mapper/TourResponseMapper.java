package com.touroperator.application.mapper;

import com.touroperator.application.dto.request.CreateTourRequest;
import com.touroperator.application.dto.response.TourResponse;
import com.touroperator.domain.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourResponseMapper {

    private TourResponseMapper() {}

    public static TourResponse toResponse(Tour tour) {
        TourResponse tourResponse = new TourResponse(
                tour.getId(),
                tour.getTourName(),
                tour.getTourDescription(),
                tour.getTourPrice(),
                tour.getDuration(),
                tour.getDateTour(),
                tour.isActive()
        );
        return tourResponse;
    }
    public static List<TourResponse> toResponse(List<Tour> tours) {
        List<TourResponse> tourResponseList = new ArrayList<>();
        for (Tour tour : tours) {
            tourResponseList.add(toResponse(tour));
        }
        return tourResponseList;
    }
    public static TourResponse toResponse(CreateTourRequest createTourRequest) {
        TourResponse tourResponse = new TourResponse(
                null,
                createTourRequest.getTourName(),
                createTourRequest.getTourDescription(),
                createTourRequest.getTourPrice(),
                createTourRequest.getDuration(),
                createTourRequest.getDateTour(),
                true
        );
        return tourResponse;
    }
}
