package com.tago.api.auth.jwt;

import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.jwt.dto.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenPublisher {

    private final JwtTokenGenerator jwtTokenGenerator;

    public JwtTokenDto generateTokens(Long memberId) {
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(memberId);
        return new JwtTokenDto(accessToken, refreshToken);
    }
}
