package com.tago.api.auth.application;

import com.tago.api.common.security.jwt.JwtTokenGenerator;
import com.tago.api.common.security.jwt.JwtTokenPublisher;
import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenReissueService {

    private final JwtTokenPublisher jwtTokenPublisher;
    private final MemberQueryService memberQueryService;

    @Transactional
    public JwtTokenDto reissue(Long memberId) {
        Member member = memberQueryService.findById(memberId);
        return jwtTokenPublisher.generateAccessToken(member.getId(), member.getRole());
    }
}
