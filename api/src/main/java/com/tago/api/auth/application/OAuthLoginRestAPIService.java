package com.tago.api.auth.application;

import com.tago.api.auth.infra.OAuthInfoResponse;
import com.tago.api.auth.infra.OAuthLoginParams;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.member.repository.MemberRepository;
import com.tago.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginRestAPIService {
    private final MemberRepository memberRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final OAuthInfoService oAuthInfoService;

    public LoginResponse login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = oAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return generateTokenToDto(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> createMember(oAuthInfoResponse));
    }

    private Long createMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .name(oAuthInfoResponse.getNickname())
                .oauthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }

    public LoginResponse generateTokenToDto(Long memberId) {
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(memberId);
        return new LoginResponse(accessToken, refreshToken);
    }
}