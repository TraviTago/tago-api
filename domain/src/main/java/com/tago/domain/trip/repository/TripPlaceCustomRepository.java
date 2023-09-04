package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.dto.TripPlaceDto;

import java.util.List;

public interface TripPlaceCustomRepository {

    List<TripPlaceDto> findAllByTrip(Trip trip);
}
