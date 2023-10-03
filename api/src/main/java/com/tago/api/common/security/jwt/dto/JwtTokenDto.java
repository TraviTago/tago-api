package com.tago.api.common.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {
    private String accessToken;
    private String refreshToken;
}
