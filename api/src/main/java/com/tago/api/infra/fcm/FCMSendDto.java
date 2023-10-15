package com.tago.api.infra.fcm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FCMSendDto {
    private String title;
    private String content;
    private String phoneNumber;
}
