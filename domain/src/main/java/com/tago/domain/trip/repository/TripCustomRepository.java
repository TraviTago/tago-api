package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPreviewDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TripCustomRepository {
    List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit);
}
