package com.tago.api.member.application;

import com.tago.api.common.mapper.MemberDtoMapper;
import com.tago.api.member.dto.response.MemberInfoResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberQueryService memberQueryService;

    @Transactional(readOnly = true)
    public MemberInfoResponse getInfo(Long memberId) {
        Member member = memberQueryService.findById(memberId);
        return MemberDtoMapper.toDto(member);
    }
}
