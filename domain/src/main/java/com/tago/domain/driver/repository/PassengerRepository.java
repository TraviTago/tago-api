package com.tago.domain.driver.repository;

import com.tago.domain.driver.dto.PassengerDto;
import com.tago.domain.trip.domain.Trip;

import java.util.List;

public interface PassengerRepository {
    List<PassengerDto> findPassengerByTrip(Trip trip);
}
