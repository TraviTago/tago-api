package com.tago.api.infra.FCM.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private String body;
    private String content;

    @Builder
    public FCMNotificationRequestDto(String body ,String content){
        this.body = body;
        this.content = content;
    }
}
