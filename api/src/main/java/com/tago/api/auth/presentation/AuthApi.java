package com.tago.api.auth.presentation;

import com.tago.api.auth.application.OAuthLoginService;
import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.application.TokenReissueService;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.dto.response.TokenReissueResponse;
import com.tago.api.common.annotation.LoginUser;
import com.tago.api.common.model.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthApi {
    private final OAuthLoginService oAuthLoginService;
    private final TokenReissueService tokenReissueService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseDto.ok(oAuthLoginService.login(loginRequest));
    }
    @PostMapping("/auth/token/reissue")
    public ResponseEntity<TokenReissueResponse> reissue(@LoginUser Long memberId) {
        TokenReissueResponse response = tokenReissueService.reissue(memberId);
        return ResponseDto.ok(response);
    }
}