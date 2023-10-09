package com.tago.api.driver.application;

import com.tago.api.driver.dto.response.DriverGetResponse;
import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.dto.DriverInfoDto;
import com.tago.domain.driver.handler.DispatchQueryService;
import com.tago.domain.driver.handler.DriverQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final TripQueryService tripQueryService;
    private final DriverQueryService driverQueryService;
    private final DispatchQueryService dispatchQueryService;

    @Transactional(readOnly = true)
    public DriverInfoDto getByTrip(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        Dispatch dispatch = dispatchQueryService.findByTrip(trip);
        return driverQueryService.findByDriverAndCar(trip.getCurrentCnt(), dispatch.getDriver());
    }

    @Transactional(readOnly = true)
    public DriverGetResponse get(Long driverId) {
        Driver driver = driverQueryService.findById(driverId);
        return new DriverGetResponse(driver.getImgUrl(), driver.getName());
    }
}
