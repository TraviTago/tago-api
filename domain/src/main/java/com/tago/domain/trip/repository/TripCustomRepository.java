package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripCustomRepository {

    List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit);
}

