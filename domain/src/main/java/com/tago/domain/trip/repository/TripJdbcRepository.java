package com.tago.domain.trip.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TripJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
}
