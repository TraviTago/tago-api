package com.tago.api.driver.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.mapper.DriverTripDtoMapper;
import com.tago.api.driver.dto.response.DriverTripGetAllResponse;
import com.tago.api.driver.dto.response.TripGetOneResponse;
import com.tago.api.driver.dto.response.TripGetResponse;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.handler.DispatchQueryService;
import com.tago.domain.driver.handler.DriverQueryService;
import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import com.tago.domain.driver.service.factory.dispatch.DispatchServiceFactory;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.handler.TripPlaceQueryService;
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
    private final TripPlaceQueryService tripPlaceQueryService;
    private final DriverQueryService driverQueryService;
    private final DispatchQueryService dispatchQueryService;
    private final DispatchServiceFactory dispatchServiceFactory;

    @Transactional
    public void dispatch(Long tripId, Long driverId, String state) {
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
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
    public TripGetOneResponse getOne(Long tripId, Long driverId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripPlaceDto> places = tripPlaceQueryService.findAll(trip);

        return TripGetOneResponse.builder()
                .tripName(trip.getName())
                .currentCnt(trip.getCurrentCnt())
                .maxCnt(trip.getMaxCnt())
                .isDispatched(isDispatched(tripId, driverId))
                .places(places)
                .build();
    }

    private Boolean isDispatched(Long tripId, Long driverId) {
        return dispatchQueryService.existsTripIdAndDriverId(tripId, driverId);
    }

    @Transactional(readOnly = true)
    public DriverTripGetAllResponse getAllByDriver(Long driverId) {
        Driver driver = driverQueryService.findById(driverId);
        List<Trip> trips = tripQueryService.findAllByDriver(driver);
        return new DriverTripGetAllResponse(DriverTripDtoMapper.toDto(trips));
    }
}
