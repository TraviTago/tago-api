package com.tago.domain.trip.repository;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TripCustomRepository {

    List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet);
    List<Trip> findByPlaceTitleKeywordContain(String keyword, Long cursorId, LocalDateTime cursorDate, int limit);

    Trip findByTripTag(Long memberId);

    List<Trip> findAllByMember(Member member);

    Optional<Trip> findByIdFetchTripMember(Long tripId);
}

