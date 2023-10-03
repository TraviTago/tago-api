package com.tago.api.auth.dto.response;

import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import com.tago.domain.member.domain.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private Long memberId;
    private Role role;
    private JwtTokenDto tokens;
}
