package com.tago.api.auth.dto.response;

import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.dto.MemberInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private Long memberId;
    private Authority authority;
}
