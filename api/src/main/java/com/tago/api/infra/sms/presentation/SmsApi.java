package com.tago.api.infra.sms.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.infra.sms.application.SmsService;
import com.tago.api.infra.sms.domain.dto.SmsRequest;
import com.tago.api.infra.sms.domain.dto.SmsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SmsApi {
    private final SmsService smsService;

    @PostMapping("/auth/sms")
    public ResponseEntity<Void> send(@RequestBody @Valid SmsRequest.Send request) {
        smsService.send(request);
        return ResponseDto.noContent();
    }

    @PostMapping("/auth/sms/verify-code")
    public ResponseEntity<SmsResponse.VerifyCode> verifyCode(@RequestBody @Valid SmsRequest.VerifyCode request) {
        SmsResponse.VerifyCode response = smsService.verifyCode(request);
        return ResponseDto.ok(response);
    }
}
