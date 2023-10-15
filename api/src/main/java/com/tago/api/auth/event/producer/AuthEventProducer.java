package com.tago.api.auth.event.producer;

import com.tago.domain.common.event.Event;

public interface AuthEventProducer {
    void produceEvent(Event event);
}
