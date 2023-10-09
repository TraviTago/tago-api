package com.tago.domain.driver.handler;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.exception.DispatchNotFoundException;
import com.tago.domain.driver.repository.DispatchRepository;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.exception.TripNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DispatchQueryService {

    private final DispatchRepository dispatchRepository;

    public Dispatch findByTripAndDriver(Trip trip, Driver driver) {
        return dispatchRepository.findByTripAndDriver(trip, driver)
                .orElseThrow(DispatchNotFoundException::new);
    }

    public Optional<Dispatch> findOptionalByTrip(Trip trip) {
        return dispatchRepository.findByTrip(trip);
    }

    public Boolean existsTripIdAndDriverId(Long tripId, Long driverId) {
        return dispatchRepository.existsByTripIdAndDriverId(tripId, driverId);
    }

    public Dispatch findByTrip(Trip trip) {
        return dispatchRepository.findByTrip(trip)
                .orElseThrow(DispatchNotFoundException::new);
    }
}
