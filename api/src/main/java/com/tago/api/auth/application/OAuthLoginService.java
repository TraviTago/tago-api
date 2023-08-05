package com.tago.api.auth.application;

import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final MemberQueryService memberQueryService;
    private final JwtTokenGenerator jwtTokenGenerator;

    public LoginResponse login(LoginRequest loginRequest){
        Member member = getOrCreateMember(loginRequest);
        return generateTokenToDto(member.getId());
    }

    private Member getOrCreateMember(LoginRequest loginRequest) {
        return memberQueryService.getOrCreateMember(
                loginRequest.getEmail(),
                loginRequest.getOauthProvider(),
                loginRequest.getName()
        );
    }

    public LoginResponse generateTokenToDto(Long memberId) {
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(memberId);
        return new LoginResponse(accessToken, refreshToken);
    }
}
