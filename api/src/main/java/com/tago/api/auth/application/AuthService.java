package com.tago.api.auth.application;

import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.dto.request.SignUpRequest;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.dto.response.SignUpResponse;
import com.tago.api.auth.jwt.JwtTokenPublisher;
import com.tago.api.common.exception.AlreadyExistsAccountException;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberCreateService;
import com.tago.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberQueryService memberQueryService;
    private final MemberCreateService memberCreateService;
    private final JwtTokenPublisher jwtTokenPublisher;

    @Transactional
    public LoginResponse login(LoginRequest request){
        Member member = memberQueryService.findByPhone(request.getNumber());
        return new LoginResponse(jwtTokenPublisher.generateTokens(member.getId()));
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        checkExistsPhoneNumber(request.getNumber());
        Member member = memberCreateService.create(request.toMemberInfoDto());

        return new SignUpResponse(
                member.getId(),
                member.getAuthority(),
                jwtTokenPublisher.generateTokens(member.getId())
        );
    }

    private void checkExistsPhoneNumber(String number) {
        memberQueryService.findOptionalByPhoneNumber(number)
                .ifPresent(member -> {throw new AlreadyExistsAccountException();});
    }
}
