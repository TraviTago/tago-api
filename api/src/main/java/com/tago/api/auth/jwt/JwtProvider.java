package com.tago.api.auth.jwt;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.service.MemberGetService;
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
    private final MemberGetService memberGetService;

    public Authentication authenticate(String token) {
        Long memberId = jwtTokenExtractor.extract(token);
        Member member = memberGetService.getMember(memberId);
        Authority authority = member.getAuthority();
        return new UsernamePasswordAuthenticationToken(member, "",
                List.of(new SimpleGrantedAuthority(authority.toString()))
        );
    }
}
