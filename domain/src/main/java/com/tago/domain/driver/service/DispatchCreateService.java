package com.tago.domain.driver.service;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.event.producer.DispatchEvent;
import com.tago.domain.driver.event.producer.DispatchEventProducer;
import com.tago.domain.driver.exception.AlreadyExistsDispatchException;
import com.tago.domain.driver.handler.DispatchCommandService;
import com.tago.domain.driver.handler.DispatchQueryService;
import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.exception.TripNotFoundException;
import com.tago.domain.tripmember.domain.TripMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchCreateService implements DispatchService {

    private static final String state = "ACCEPT";
    private final DispatchCommandService dispatchCommandService;
    private final DispatchQueryService dispatchQueryService;
    private final DispatchEventProducer dispatchEventProducer;

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
        producerEvent(trip);
    }

    private void validateDispatchAble(Trip trip) {
        dispatchQueryService.findOptionalByTrip(trip)
                .ifPresent(dispatch -> {throw new AlreadyExistsDispatchException();});
    }

    private void producerEvent(Trip trip) {
        trip.getTripMembers().forEach(this::publish);
    }

    public void publish(TripMember tripMember) {
        dispatchEventProducer.produceEvent(DispatchEvent.builder()
                .name(tripMember.getMember().getName())
                .phoneNumber(tripMember.getMember().getPhoneNumber())
                .action(DispatchEvent.Action.CREATE)
                .build()
        );
    }
}
