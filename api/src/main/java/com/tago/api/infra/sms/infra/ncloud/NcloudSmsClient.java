package com.tago.api.infra.sms.infra.ncloud;

import com.tago.api.infra.sms.domain.SmsClient;
import com.tago.api.infra.sms.domain.dto.SmsRequest;
import com.tago.api.infra.sms.infra.ncloud.dto.NcloudSmsRequest;
import com.tago.api.infra.sms.util.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.tago.api.infra.sms.infra.ncloud.dto.NcloudSmsRequest.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NcloudSmsClient implements SmsClient {

    private final NcloudSmsFeignClient ncloudSmsFeignClient;
    private final NcloudSignatureMaker ncloudSignatureMaker;
    private final NcloudProperties ncloudProperties;
    private final VerificationCodeUtil verificationCodeUtil;

    @Override
    public void send(SmsRequest.Send dto) {
        ncloudSmsFeignClient.send(
                makeHeaders(),
                makeRequest(dto)
        );
    }

    private NcloudSmsRequest makeRequest(SmsRequest.Send dto) {
        return NcloudSmsRequest.builder()
                .type("SMS")
                .from(ncloudProperties.getCallingNumber())
                .content(generateVerificationCode(dto))
                .messages(getMessages(dto))
                .build();
    }

    private HttpHeaders makeHeaders() {
        Long timestamp = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", timestamp.toString());
        headers.set("x-ncp-iam-access-key", ncloudProperties.getAccessKey());
        headers.set("x-ncp-apigw-signature-v2", ncloudSignatureMaker.makeSignature(timestamp));
        return headers;
    }

    private List<Message> getMessages(SmsRequest.Send dto) {
        return List.of(new Message(dto.getNumber()));
    }

    private String generateVerificationCode(SmsRequest.Send dto) {
        String code = verificationCodeUtil.generate(dto.getNumber(), 60 * 3L);
        return "Your verification code is: <#> " + code;
    }
}
