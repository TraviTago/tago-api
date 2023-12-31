package com.tago.api.auth.infra;

import com.tago.domain.member.domain.vo.OAuthProvider;
import org.springframework.util.MultiValueMap;

//OAuth요청을 위한 파라미터 값들을 갖고 있는 인터페이스
public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}