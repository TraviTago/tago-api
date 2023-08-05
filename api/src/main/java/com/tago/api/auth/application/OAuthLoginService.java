package com.tago.api.auth.application;

import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.dto.LoginResponse;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.jwt.JwtTokenGenerator;
import com.tago.domain.auth.domain.OAuthInfoResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.repository.MemberRepository;
import com.tago.domain.member.service.MemberGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthSdkService {

    //1. email 과 sns 받아서 email 이 기존 유저인지 확인한다..
    //2. 이메일에 해당하는 유저가 없으면 유저를 생성한다.
    //3. aha 유저아이디를 포함한 엑세스 토근과 refresh token make
    //4. login response token 값을 넣어서 반환한다.

    private final MemberGetService memberGetService;
    private final MemberRepository memberRepository;
    private final JwtTokenGenerator jwtTokenGenerator;

    public LoginResponse login(LoginRequest loginRequest){
        Member member = findOrCreateMember(loginRequest);
        return generateTokenToDto(member.getId());
    }

    private Member findOrCreateMember(LoginRequest loginRequest) {
        memberGetService.getMember(loginRequest.getEmail(), loginRequest.getSns());

        return memberRepository.findByEmail(loginRequest.getEmail())
                .orElseGet(() -> createMember(loginRequest));
    }

    private Member createMember(LoginRequest loginRequest) {
        Member member = Member.builder()
                .email(loginRequest.getEmail())
                .build();

        return memberRepository.save(member);
    }

    public LoginResponse generateTokenToDto(Long memberId) {
        String accessToken = jwtTokenGenerator.generateAccessToken(memberId);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(memberId);
        return new LoginResponse(accessToken, refreshToken);
    }


}
