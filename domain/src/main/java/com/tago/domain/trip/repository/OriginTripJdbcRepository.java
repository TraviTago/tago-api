package com.tago.domain.trip.repository;

import com.tago.domain.trip.dto.OriginTripCreateDto;

import java.util.List;

public interface OriginTripJdbcRepository {

    void saveAll(List<OriginTripCreateDto> commands);
}
