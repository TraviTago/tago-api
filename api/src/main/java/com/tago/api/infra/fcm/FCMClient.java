package com.tago.api.infra.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.tago.api.common.exception.FailedSendFCMException;
import com.tago.api.common.exception.NotExistsFirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMClient {

    private final FCMTokenUtil fcmTokenUtil;

    public void send(FCMSendDto dto) {
        validate(dto.getPhoneNumber());

        String token = fcmTokenUtil.get(dto.getPhoneNumber());
        Message message = Message.builder()
                .putData("title", dto.getTitle())
                .putData("content", dto.getContent())
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            throw new FailedSendFCMException(e);
        }
    }

    private void validate(String phoneNumber) {
        if (!fcmTokenUtil.hasKey(phoneNumber)) {
            throw new NotExistsFirebaseToken();
        }
    }
}
