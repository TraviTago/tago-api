package com.tago.api.auth.application;


import com.tago.domain.auth.domain.OAuthApiClient;
import com.tago.domain.auth.domain.OAuthInfoResponse;
import com.tago.domain.auth.domain.OAuthLoginParams;
import com.tago.domain.auth.domain.OAuthProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public OAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}