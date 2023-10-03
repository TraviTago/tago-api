package com.tago.api.common.security.jwt;

import com.tago.api.common.security.jwt.dto.JwtTokenDto;
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
