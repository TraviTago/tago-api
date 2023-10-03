package com.tago.api.common.security.jwt;

import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import com.tago.domain.member.domain.vo.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenPublisher {

    private final JwtTokenGenerator jwtTokenGenerator;

    public JwtTokenDto generateTokens(Long id, Role role) {
        String accessToken = jwtTokenGenerator.generateToken(id, role, JwtTokenGenerator.ACCESS_TOKEN_EXPIRE_TIME);
        String refreshToken = jwtTokenGenerator.generateToken(id, role, JwtTokenGenerator.REFRESH_TOKEN_EXPIRE_TIME);
        return new JwtTokenDto(accessToken, refreshToken);
    }

    public JwtTokenDto generateAccessToken(Long id, Role role) {
        String accessToken = jwtTokenGenerator.generateToken(id, role, JwtTokenGenerator.ACCESS_TOKEN_EXPIRE_TIME);
        return new JwtTokenDto(accessToken, null);
    }
}
