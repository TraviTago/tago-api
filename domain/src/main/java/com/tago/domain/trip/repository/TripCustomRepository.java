package com.tago.domain.trip.repository;

import com.tago.domain.driver.domain.Driver;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.trip.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TripCustomRepository {

    List<Trip> findAll(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet, Gender memberGender);
    List<Trip> findByPlaceTitleKeywordContain(String keyword, Long cursorId, LocalDateTime cursorDate, int limit);

    Trip findByTripTag(Long memberId);

    List<Trip> findAllByMember(Member member);

    Optional<Trip> findByIdFetchTripMember(Long tripId);

    List<Trip> findAllByNotDispatch(Long cursorId, LocalDateTime cursorDate, int limit);

    List<Trip> findAllByDriver(Driver driver);

}

