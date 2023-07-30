package com.tago.api.auth.application;

import com.tago.domain.auth.model.OAuthInfoResponse;
import com.tago.domain.auth.model.OAuthLoginParams;
import com.tago.api.auth.dto.LoginResponse;
import com.tago.domain.member.dao.repository.MemberRepository;
import com.tago.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final TokenGenerateService tokenGenerateService;
    private final OAuthInfoService oAuthInfoService;

    public LoginResponse login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = oAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return tokenGenerateService.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> createMember(oAuthInfoResponse));
    }

    private Long createMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}