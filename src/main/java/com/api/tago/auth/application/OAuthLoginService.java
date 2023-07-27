package com.api.tago.auth.application;

import com.api.tago.auth.domain.AuthTokens;
import com.api.tago.auth.domain.AuthTokensGenerator;
import com.api.tago.auth.domain.oauth.OAuthInfoResponse;
import com.api.tago.auth.domain.oauth.OAuthLoginParams;
import com.api.tago.auth.domain.oauth.RequestOAuthInfoService;
import com.api.tago.member.domain.MemberRepository;
import com.api.tago.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}