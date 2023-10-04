package com.tago.api.driver.application;

import com.tago.api.driver.dto.response.DriverGetResponse;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.dto.DriverInfoDto;
import com.tago.domain.driver.handler.DriverQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverQueryService driverQueryService;
    private final TripQueryService tripQueryService;

    @Transactional(readOnly = true)
    public DriverInfoDto getByTrip(Long tripId, Long driverId) {
        Trip trip = tripQueryService.findById(tripId);
        return driverQueryService.findById(trip.getCurrentCnt(), driverId);
    }

    @Transactional(readOnly = true)
    public DriverGetResponse get(Long driverId) {
        Driver driver = driverQueryService.findById(driverId);
        return new DriverGetResponse(driver.getImgUrl(), driver.getName());
    }
}
