package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.OriginTrip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OriginTripRepository extends JpaRepository<OriginTrip, Long>, OriginTripJdbcRepository {

    Optional<OriginTrip> findByName(String name);
}
