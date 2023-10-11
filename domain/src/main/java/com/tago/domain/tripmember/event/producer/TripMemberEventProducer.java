package com.tago.domain.tripmember.event.producer;

import com.tago.domain.common.event.Event;

public interface TripMemberEventProducer {

    void produceEvent(Event event);
}
