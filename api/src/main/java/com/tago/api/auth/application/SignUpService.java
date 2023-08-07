package com.tago.api.auth.application;

import com.tago.api.auth.dto.request.SignUpRequest;
import com.tago.api.auth.dto.response.SignUpResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.dto.MemberInfoDto;
import com.tago.domain.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final MemberUpdateService memberUpdateService;

    @Transactional
    public SignUpResponse signUp(Long memberId, SignUpRequest request) {
        Member member = updateMemberInfo(memberId, request);
        return new SignUpResponse(member.getId(), member.getAuthority());
    }

    private Member updateMemberInfo(Long memberId, SignUpRequest request) {
        MemberInfoDto memberInfoDto = request.toMemberInfoDto();
        return memberUpdateService.updateMemberInfo(memberId, memberInfoDto);
    }
}
