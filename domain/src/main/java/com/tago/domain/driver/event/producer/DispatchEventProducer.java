package com.tago.domain.driver.event.producer;

import com.tago.domain.common.event.Event;

public interface DispatchEventProducer {
    void produceEvent(Event event);
}
