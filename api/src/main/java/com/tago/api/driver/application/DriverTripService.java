package com.tago.api.driver.application;

import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.handler.DriverQueryService;
import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import com.tago.domain.driver.service.factory.dispatch.DispatchServiceFactory;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverDispatchService {

    private final TripQueryService tripQueryService;
    private final DriverQueryService driverQueryService;
    private final DispatchServiceFactory dispatchServiceFactory;

    @Transactional
    public void dispatch(Long tripId, Long driverId, String state) {
        Trip trip = tripQueryService.findById(tripId);
        Driver driver = driverQueryService.findById(driverId);
        DispatchService service = dispatchServiceFactory.getInstance(state);
        service.dispatch(trip, driver);
    }
}
