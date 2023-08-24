package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripPlaceRepository extends JpaRepository<TripPlace, Long>, TripPlaceCustomRepository {
}
