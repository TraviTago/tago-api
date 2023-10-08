package com.tago.api.driver.application;

import com.tago.api.driver.dto.response.PassengerGetResponse;
import com.tago.domain.driver.dto.PassengerDto;
import com.tago.domain.driver.service.PassengerQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerGetService {
    private final PassengerQueryService passengerQueryService;
    private final TripQueryService tripQueryService;

    @Transactional(readOnly = true)
    public PassengerGetResponse getPassengerByTip(Long tripId){
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
        List<PassengerDto> members = passengerQueryService.findPassengerByTrip(trip);
        return new PassengerGetResponse(members);
    }

}
