package com.tago.domain.driver.handler;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.exception.DispatchNotFoundException;
import com.tago.domain.driver.repository.DispatchRepository;
import com.tago.domain.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchQueryService {

    private final DispatchRepository dispatchRepository;

    public Dispatch findByTripAndDriver(Trip trip, Driver driver) {
        return dispatchRepository.findByTripAndDriver(trip, driver)
                .orElseThrow(DispatchNotFoundException::new);
    }
}
