package com.tago.api.member.application;

import com.tago.api.member.dto.response.MemberAuthInfoResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberQueryService memberQueryService;

    @Transactional(readOnly = true)
    public MemberAuthInfoResponse getAuthInfo(Long memberId) {
        Member member = memberQueryService.getMember(memberId);
        boolean isSignedUp = member.isSignedUp();

        return new MemberAuthInfoResponse(
                member.getEmail(),
                member.getOauthProvider(),
                isSignedUp
        );
    }
}
