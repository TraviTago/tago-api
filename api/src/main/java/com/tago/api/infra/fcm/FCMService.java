package com.tago.api.infra.fcm;

import com.tago.api.auth.event.AuthEvent;
import com.tago.domain.driver.event.producer.DispatchEvent;
import com.tago.domain.tripmember.event.producer.TripMemberEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FCMService {

    private final FCMClient fcmClient;
    private final FCMTokenUtil fcmTokenUtil;

    public void sendByTripMemberEvent(TripMemberEvent event) {
        FCMType type = FCMType.from(event.getAction());
        fcmClient.send(FCMSendDto.builder()
                .payload(String.valueOf(event.getTripId()))
                .title(type.getTitle())
                .content(type.getContent(event.getName()))
                .phoneNumber(event.getPhoneNumber())
                .build());
    }

    public void sendByDispatchEvent(DispatchEvent event) {
        FCMType type = FCMType.CREATE_DISPATCH;
        fcmClient.send(FCMSendDto.builder()
                .title(type.getTitle())
                .content(type.getContent(event.getName()))
                .phoneNumber(event.getPhoneNumber())
                .build());
    }

    public void saveToken(AuthEvent event) {
        fcmTokenUtil.save(event.getPhoneNumber(), event.getFirebaseToken());
    }
}
