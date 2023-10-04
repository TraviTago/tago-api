package com.tago.domain.driver.repository;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispatchRepository extends JpaRepository<Dispatch,Long> {
    Optional<Dispatch> findByTripAndDriver(Trip trip, Driver driver);
    Optional<Dispatch> findByTrip(Trip trip);
}
