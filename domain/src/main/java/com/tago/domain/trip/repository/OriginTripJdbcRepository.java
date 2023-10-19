package com.tago.domain.trip.repository;

import com.tago.domain.trip.dto.OriginTripCreateDto;
import com.tago.domain.trip.dto.OriginTripPlaceCreateDto;

import java.util.List;

public interface OriginTripJdbcRepository {

    void saveAllTrip(List<OriginTripCreateDto> commands);
    void saveAllTripPlace(List<OriginTripPlaceCreateDto> commands);
    Long lastInsertId();
}
