package com.tago.api.common.security.jwt;

import com.tago.api.common.security.jwt.dto.JwtTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtTokenExtractor jwtTokenExtractor;

    public Authentication authenticate(String token) {
        JwtTokenInfo info = jwtTokenExtractor.extract(token);
        return new UsernamePasswordAuthenticationToken(info.getId(), "",
                List.of(new SimpleGrantedAuthority(info.getRole()))
        );
    }
}
