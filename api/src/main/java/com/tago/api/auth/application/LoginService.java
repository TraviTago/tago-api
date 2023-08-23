package com.tago.api.auth.application;

import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberCreateService memberCreateService;
    private final JwtTokenGenerator jwtTokenGenerator;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest){
        Member member = getOrCreateMember(loginRequest);
        return generateTokenToDto(member.getId());
    }

    private Member getOrCreateMember(LoginRequest loginRequest) {
        return memberCreateService.getOrCreateMember(
                loginRequest.getEmail(),
                loginRequest.getName(),
                loginRequest.getImgUrl(),
                loginRequest.getOauthProvider()
        );
    }

    public LoginResponse generateTokenToDto(Long memberId) {
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(memberId);
        return new LoginResponse(accessToken, refreshToken);
    }
}
