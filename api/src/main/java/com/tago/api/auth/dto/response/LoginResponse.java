package com.tago.api.auth.dto.response;

import com.tago.api.auth.jwt.dto.JwtTokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private JwtTokenDto tokens;
}