package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long>, TripCustomRepository {
    List<Trip> findByNameAndDateTimeAfter(String name, LocalDateTime dateTime);
    Boolean existsByMemberId(Long memberId);
}
