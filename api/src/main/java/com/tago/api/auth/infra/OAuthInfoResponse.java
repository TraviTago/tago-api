package com.tago.api.auth.infra;

import com.tago.domain.member.domain.vo.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}