package com.tago.api.auth.event;

import com.tago.domain.common.event.Event;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthEvent implements Event {
    private String phoneNumber;
    private String firebaseToken;
}
