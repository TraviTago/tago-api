package com.tago.api.auth.application;

import com.tago.api.auth.dto.response.TokenReissueResponse;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.member.handler.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenReissueService {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final MemberQueryService memberQueryService;

    @Transactional
    public TokenReissueResponse reissue(Long memberId) {
        existsMember(memberId);
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        return new TokenReissueResponse(accessToken);
    }

    private void existsMember(Long memberId) {
        memberQueryService.findById(memberId);
    }
}
