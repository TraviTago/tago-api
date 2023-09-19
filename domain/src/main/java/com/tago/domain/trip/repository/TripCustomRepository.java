package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripCustomRepository {

    List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet);
    List<Trip> findByPlaceTitleKeywordContain(String keyword, Long cursorId, LocalDateTime cursorDate, int limit);

    Trip findByTripTag(Long memberId);
}

