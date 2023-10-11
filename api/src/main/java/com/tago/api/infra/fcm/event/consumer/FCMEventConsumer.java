package com.tago.api.infra.fcm.event.consumer;

import com.tago.api.auth.event.AuthEvent;
import com.tago.api.infra.fcm.FCMService;
import com.tago.domain.driver.event.producer.DispatchEvent;
import com.tago.domain.tripmember.event.producer.TripMemberEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class FCMEventConsumer {

    private final FCMService fcmService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void consume(TripMemberEvent event) {
        fcmService.sendByTripMemberEvent(event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void consume(DispatchEvent event) {
        fcmService.sendByDispatchEvent(event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void consume(AuthEvent event) {
        fcmService.saveToken(event);
    }
}
