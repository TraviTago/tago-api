package com.tago.api.member.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.member.application.MemberService;
import com.tago.api.member.dto.response.MemberAuthInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApi {

    public final MemberService memberService;

    @GetMapping("/members/me")
    public ResponseEntity<MemberAuthInfoResponse> getAuthInfo(
            @LoginMember Long memberId
    ) {
        MemberAuthInfoResponse response = memberService.getAuthInfo(memberId);
        return ResponseDto.ok(response);
    }
}
