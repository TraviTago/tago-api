package com.tago.api.auth.event.producer.impl;

import com.tago.api.auth.event.producer.AuthEventProducer;
import com.tago.domain.common.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationAuthEventProducer implements AuthEventProducer {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void produceEvent(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
