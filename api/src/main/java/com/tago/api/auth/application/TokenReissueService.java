package com.tago.api.auth.application;

import com.tago.api.auth.dto.response.TokenReissueResponse;
import com.tago.api.auth.jwt.JwtTokenExtractor;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenReissueService {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final MemberQueryService memberGetService;

    public TokenReissueResponse reissue(Long memberId) {
        existsMember(memberId);
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        return new TokenReissueResponse(accessToken);
    }

    private void existsMember(Long memberId) {
        memberGetService.getMember(memberId);
    }
}
