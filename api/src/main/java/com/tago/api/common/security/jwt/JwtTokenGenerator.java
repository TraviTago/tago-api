package com.tago.api.common.security.jwt;

import com.tago.domain.member.domain.vo.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final JwtProperties jwtProperties;

    public String generateToken(Long id, Role role, Long expiredAt) {
        long now = (new Date()).getTime();
        Date tokenExpiredAt = new Date(now + expiredAt);
        return generate(id, role, tokenExpiredAt);
    }

    private String generate(Long id, Role role, Date expiredAt) {
        Map<String, Object> claims = createClaims(id, role);
        Key key = jwtProperties.getKey();

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private Map<String, Object> createClaims(Long id, Role role) {
        return Map.of("id", id, "role", role.name());
    }
}
