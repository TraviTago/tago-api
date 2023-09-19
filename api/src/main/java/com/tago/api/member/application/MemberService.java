package com.tago.api.member.application;

import com.tago.api.common.mapper.MemberDtoMapper;
import com.tago.api.member.dto.request.MemberUpdateRequest;
import com.tago.api.member.dto.response.MemberGetResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberQueryService memberQueryService;
    private final MemberUpdateService memberUpdateService;

    @Transactional(readOnly = true)
    public MemberGetResponse get(Long memberId) {
        Member member = memberQueryService.findByMemberId(memberId);
        return MemberDtoMapper.toDto(member);
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequest request) {
        memberUpdateService.updateMemberInfo(memberId, request.toMemberUpdateDto());
    }
}
