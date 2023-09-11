package com.tago.api.infra.sms.infra.ncloud;

import com.tago.api.infra.sms.infra.ncloud.dto.NcloudSmsRequest;
import com.tago.api.infra.sms.infra.ncloud.dto.NcloudSmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "ncloudSmsFeignClient", url = "${ncloud.sms.base-url}")
public interface NcloudSmsFeignClient {

    @PostMapping("/messages")
    NcloudSmsResponse send(
            @RequestHeader HttpHeaders headers,
            NcloudSmsRequest request
    );
}
