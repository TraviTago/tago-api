package com.tago.api.auth.dto.response;

import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private JwtTokenDto tokens;
}