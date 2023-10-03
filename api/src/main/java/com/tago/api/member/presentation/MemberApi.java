package com.tago.api.member.presentation;

import com.tago.api.common.security.annotation.UserAuthentication;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.member.application.MemberService;
import com.tago.api.member.dto.request.MemberUpdateRequest;
import com.tago.api.member.dto.response.MemberGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApi {

    public final MemberService memberService;

    @GetMapping("/members/me")
    public ResponseEntity<MemberGetResponse> get(
            @UserAuthentication Long memberId
    ) {
        MemberGetResponse response = memberService.get(memberId);
        return ResponseDto.ok(response);
    }

    @PatchMapping("/members/me")
    public ResponseEntity<Void> update(
            @UserAuthentication Long memberId,
            @RequestBody MemberUpdateRequest request
    ) {
        memberService.update(memberId, request);
        return ResponseDto.noContent();
    }
}
