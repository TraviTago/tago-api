package com.tago.api.infra.sms.application;

import com.tago.api.infra.sms.domain.SmsClient;
import com.tago.api.infra.sms.domain.dto.SmsRequest;
import com.tago.api.infra.sms.domain.dto.SmsResponse;
import com.tago.api.infra.sms.util.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsClient smsClient;
    private final VerificationCodeUtil verificationCodeUtil;

    public void send(SmsRequest.Send request) {
        smsClient.send(request);
    }

    public SmsResponse.VerifyCode verifyCode(SmsRequest.VerifyCode request) {
        verificationCodeUtil.verify(request.getNumber(), request.getCode());
        return new SmsResponse.VerifyCode(true);
    }
}

