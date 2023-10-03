package com.tago.api.auth.jwt;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.handler.MemberQueryService;
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
    private final MemberQueryService memberQueryService;

    public Authentication authenticate(String token) {
        Long memberId = jwtTokenExtractor.extract(token);
        Member member = memberQueryService.findById(memberId);
        Authority authority = member.getAuthority();
        return new UsernamePasswordAuthenticationToken(member.getId(), "",
                List.of(new SimpleGrantedAuthority(authority.getRole()))
        );
    }
}
