package com.tago.domain.trip.service;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.repository.TripPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlaceQueryService {

    private final TripPlaceRepository tripPlaceRepository;

    public List<TripPlaceDto> findAll(Trip trip) {
        return tripPlaceRepository.findAllByTrip(trip);
    }
}
