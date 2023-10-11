package com.tago.domain.tripmember.event.producer.impl;

import com.tago.domain.common.event.Event;
import com.tago.domain.tripmember.event.producer.TripMemberEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationTripMemberEventProducer implements TripMemberEventProducer {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void produceEvent(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
