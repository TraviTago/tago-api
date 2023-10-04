package com.tago.domain.driver.service;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.exception.AlreadyExistsDispatchException;
import com.tago.domain.driver.handler.DispatchCommandService;
import com.tago.domain.driver.handler.DispatchQueryService;
import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.exception.TripNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchCreateService implements DispatchService {

    private static final String state = "ACCEPT";
    private final DispatchCommandService dispatchCommandService;
    private final DispatchQueryService dispatchQueryService;

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void dispatch(Trip trip, Driver driver) {
        validateDispatchAble(trip);
        Dispatch dispatch = Dispatch.builder()
                .trip(trip)
                .driver(driver)
                .build();

        dispatchCommandService.save(dispatch);
    }

    private void validateDispatchAble(Trip trip) {
        dispatchQueryService.findOptionalByTrip(trip)
                .ifPresent(dispatch -> {throw new AlreadyExistsDispatchException();});
    }
}
