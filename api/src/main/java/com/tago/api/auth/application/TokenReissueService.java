package com.tago.api.auth.application;

import com.tago.api.auth.dto.response.TokenReissueResponse;
import com.tago.api.auth.jwt.JwtTokenExtractor;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.member.service.MemberGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenReissueService {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenExtractor jwtTokenExtractor;
    private final MemberGetService memberGetService;

    public TokenReissueResponse reissue(String refreshToken) {
        Long memberId = jwtTokenExtractor.extract(refreshToken);
        existsMember(memberId);
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        return new TokenReissueResponse(accessToken);
    }
    private void existsMember(Long memberId) {
        memberGetService.getMember(memberId);
    }
}
