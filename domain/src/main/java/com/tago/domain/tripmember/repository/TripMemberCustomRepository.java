package com.tago.domain.tripmember.repository;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.TripMemberDto;

import java.util.List;

public interface TripMemberCustomRepository {
    List<TripMemberDto> findMembersByTrip(Trip trip);
}
