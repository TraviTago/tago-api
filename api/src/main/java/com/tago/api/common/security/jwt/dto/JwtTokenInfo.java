package com.tago.api.common.security.jwt.dto;

import com.tago.domain.member.domain.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenInfo {
    private Long id;
    private String role;
}
