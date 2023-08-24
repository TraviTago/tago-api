package com.tago.api.trip.application;

import com.tago.api.trip.dto.response.TripGetOneResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.service.TripPlaceQueryService;
import com.tago.domain.trip.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlaceService {

    private final TripQueryService tripQueryService;
    private final TripPlaceQueryService tripPlaceQueryService;

    @Transactional(readOnly = true)
    public TripGetOneResponse getTripAndPlaces(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripPlaceDto> tripPlaces = tripPlaceQueryService.findAll(trip);

        return new TripGetOneResponse(
                trip.getName(),
                trip.getCurrentCnt(),
                trip.getMaxCnt(),
                tripPlaces
        );
    }
}
