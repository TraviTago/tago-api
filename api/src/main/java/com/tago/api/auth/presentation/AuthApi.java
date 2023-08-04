package com.tago.api.auth.presentation;


import com.tago.api.auth.application.OAuthLoginService;
import com.tago.api.auth.application.OAuthSdkService;
import com.tago.api.auth.dto.LoginRequest;
import com.tago.api.auth.dto.LoginResponse;
import com.tago.api.auth.infra.kakao.KakaoLoginParams;
import com.tago.api.common.model.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthApi {
    private final OAuthLoginService oAuthLoginService;
    private final OAuthSdkService oAuthSdkService;

    @PostMapping("/auth/kakao")
    public ResponseEntity<LoginResponse> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseDto.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginKakao(@RequestBody LoginRequest loginRequest){
        return ResponseDto.ok(oAuthSdkService.login(loginRequest));
    }

}