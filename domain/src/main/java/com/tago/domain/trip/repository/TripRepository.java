package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
