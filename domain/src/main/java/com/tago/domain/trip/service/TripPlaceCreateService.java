package com.tago.domain.trip.service;

import com.tago.domain.place.domain.Place;
import com.tago.domain.place.handler.PlaceQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.dto.TripCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlaceCreateService {
    private final PlaceQueryService placeQueryService;

    public List<TripPlace> createTripPlaces(Trip trip, List<TripCreateDto.Place> placeDtos) {
        return placeDtos.stream()
                .map(dto -> createTripPlace(trip, dto))
                .toList();
    }

    private TripPlace createTripPlace(Trip trip, TripCreateDto.Place dto) {
        Place place = placeQueryService.findById(dto.getPlaceId());

        return TripPlace.builder()
                .order(dto.getOrder())
                .trip(trip)
                .place(place)
                .build();
    }
}
