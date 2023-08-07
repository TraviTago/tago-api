package com.tago.api.auth.presentation;

import com.tago.api.auth.application.OAuthLoginService;
import com.tago.api.auth.application.SignUpService;
import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.application.TokenReissueService;
import com.tago.api.auth.dto.request.SignUpRequest;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.dto.response.SignUpResponse;
import com.tago.api.auth.dto.response.TokenReissueResponse;
import com.tago.api.common.annotation.LoginMember;
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
    private final SignUpService signUpService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseDto.ok(oAuthLoginService.login(request));
    }

    @PatchMapping("/auth/sign-up")
    public ResponseEntity<SignUpResponse> signUp(
            @LoginMember Long memberId,
            @RequestBody SignUpRequest request
    ) {
        SignUpResponse response = signUpService.signUp(memberId, request);
        return ResponseDto.ok(response);
    }

    @PostMapping("/auth/token/reissue")
    public ResponseEntity<TokenReissueResponse> reissue(@LoginMember Long memberId) {
        TokenReissueResponse response = tokenReissueService.reissue(memberId);
        return ResponseDto.ok(response);
    }
}