package com.tago.domain.driver.event.producer.impl;

import com.tago.domain.common.event.Event;
import com.tago.domain.driver.event.producer.DispatchEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationDispatchEventProducer implements DispatchEventProducer {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void produceEvent(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
