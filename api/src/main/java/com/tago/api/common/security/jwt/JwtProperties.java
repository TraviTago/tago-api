package com.tago.api.common.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.Key;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final Key key;

    public JwtProperties(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}
