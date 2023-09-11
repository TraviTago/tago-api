package com.tago.api.infra.sms.domain;

import com.tago.api.infra.sms.domain.dto.SmsRequest;

public interface SmsClient {
    void send(SmsRequest.Send dto);
}
