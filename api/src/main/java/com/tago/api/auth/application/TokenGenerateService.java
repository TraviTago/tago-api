package com.tago.api.auth.application;

import com.tago.api.auth.dto.LoginResponse;
import com.tago.domain.auth.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenGenerateService {
    private final JwtTokenGenerator jwtTokenGenerator;

    public LoginResponse generate(Long memberId) {
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(memberId);
        return new LoginResponse(accessToken, refreshToken);
    }
}
