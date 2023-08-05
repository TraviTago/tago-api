package com.tago.api.auth.presentation;


import com.tago.api.auth.application.OAuthLoginService;
import com.tago.api.auth.application.OAuthSdkService;
import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.application.TokenReissueService;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.dto.response.TokenReissueResponse;
import com.tago.api.common.model.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthApi {
    private final OAuthLoginService oAuthLoginService;
    private final OAuthSdkService oAuthSdkService;
    private final TokenReissueService tokenReissueService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseDto.ok(oAuthSdkService.login(loginRequest));
    }
    @PostMapping("/auth/token/reissue")
    public ResponseEntity<TokenReissueResponse> reissue(
            @RequestHeader("Authorization") String refreshToken
    ) {
        TokenReissueResponse response = tokenReissueService.reissue(refreshToken);
        return ResponseDto.ok(response);
    }
}