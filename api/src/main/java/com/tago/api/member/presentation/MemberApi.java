package com.tago.api.member.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.member.application.MemberService;
import com.tago.api.member.dto.request.EditMemberInfoRequest;
import com.tago.api.member.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApi {

    public final MemberService memberService;

    @GetMapping("/members/me")
    public ResponseEntity<MemberInfoResponse> getAuthInfo(
            @LoginMember Long memberId
    ) {
        MemberInfoResponse response = memberService.getInfo(memberId);
        return ResponseDto.ok(response);
    }

    @PostMapping("/members/me/edit")
    public ResponseEntity<Void> editMemberInfo(
            @LoginMember Long memberId,
            @RequestBody EditMemberInfoRequest request
    ) {
        memberService.editMemberInfo(memberId, request);
        return ResponseDto.noContent();
    }


}
