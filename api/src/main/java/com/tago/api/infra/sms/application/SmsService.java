package com.tago.api.infra.sms.application;

import com.tago.api.infra.sms.domain.SmsClient;
import com.tago.api.infra.sms.domain.dto.SmsRequest;
import com.tago.api.infra.sms.domain.dto.SmsResponse;
import com.tago.api.infra.sms.util.VerificationCodeUtil;
import com.tago.domain.member.handler.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsClient smsClient;
    private final VerificationCodeUtil verificationCodeUtil;
    private final MemberQueryService memberQueryService;

    public void send(SmsRequest.Send request) {
        smsClient.send(request);
    }

    public SmsResponse.VerifyCode verifyCode(SmsRequest.VerifyCode request) {
        verificationCodeUtil.verify(request.getNumber(), request.getCode());
        return new SmsResponse.VerifyCode(true, isSignUp(request.getNumber()));
    }

    public Boolean isSignUp(String number) {
        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
        String phoneNumber = number.replaceAll(regEx, "$1-$2-$3");
        return memberQueryService.existsByPhoneNumber(phoneNumber);
    }
}

