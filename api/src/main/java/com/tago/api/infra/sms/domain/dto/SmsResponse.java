package com.tago.api.infra.sms.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SmsResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AuthSmsVerifyCodeResponse")
    public static class VerifyCode {
        private Boolean isVerify;
        private Boolean isSignUp;
    }
}