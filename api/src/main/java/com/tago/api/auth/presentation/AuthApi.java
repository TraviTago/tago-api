package com.tago.api.auth.presentation;

import com.tago.api.auth.application.AuthService;
import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.application.TokenReissueService;
import com.tago.api.auth.dto.request.SignUpRequest;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.dto.response.SignUpResponse;
import com.tago.api.common.security.annotation.UserAuthentication;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthApi {
    private final AuthService authService;
    private final TokenReissueService tokenReissueService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseDto.ok(authService.login(request));
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        SignUpResponse response = authService.signUp(request);
        return ResponseDto.ok(response);
    }

    @PostMapping("/auth/token/reissue")
    public ResponseEntity<JwtTokenDto> reissue(@UserAuthentication Long memberId) {
        JwtTokenDto response = tokenReissueService.reissue(memberId);
        return ResponseDto.ok(response);
    }

    @DeleteMapping("/auth/withdraw")
    public ResponseEntity<?> withdraw(@UserAuthentication Long memberId) {
        authService.withdraw(memberId);
        return ResponseEntity.noContent().build();
    }
}