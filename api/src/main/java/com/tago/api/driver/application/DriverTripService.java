package com.tago.api.driver.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.mapper.DriverTripDtoMapper;
import com.tago.api.common.mapper.TripDtoMapper;
import com.tago.api.driver.dto.response.DriverTripResponse;
import com.tago.api.driver.dto.response.TripGetResponse;
import com.tago.api.trip.dto.response.MyTripGetResponse;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.handler.DriverQueryService;
import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import com.tago.domain.driver.service.factory.dispatch.DispatchServiceFactory;
import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverTripService {

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

    @Transactional(readOnly = true)
    public PageResponseDto<TripGetResponse> getAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<Trip> trips = tripQueryService.findAllByNotDispatch(cursorId, cursorDate, limit);
        List<TripGetResponse> dto = DriverTripDtoMapper.toDto(trips);
        return PageResponseDto.from(dto);
    }

    @Transactional(readOnly = true)
    public DriverTripResponse getAllByDriver(Long driverId) {
        Driver driver = driverQueryService.findById(driverId);
        List<Trip> trips = tripQueryService.findAllByDriver(driver);
        return new DriverTripResponse(DriverTripDtoMapper.toDto(trips));
    }
}
