package com.tago.domain.driver.service;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.handler.DispatchCommandService;
import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import com.tago.domain.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchCreateService implements DispatchService {

    private static final String state = "ACCEPT";
    private final DispatchCommandService dispatchCommandService;

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void dispatch(Trip trip, Driver driver) {
        Dispatch dispatch = Dispatch.builder()
                .trip(trip)
                .driver(driver)
                .build();

        dispatchCommandService.save(dispatch);
    }
}
