package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.dto.MemberUpdateDto;
import com.tago.domain.member.handler.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final MemberQueryService memberQueryService;
    private final MemberTagCreateService memberTagCreateService;

    public void updateMemberInfo(Long memberId, MemberUpdateDto dto) {
        Member member = memberQueryService.findByIdFetchMemberTag(memberId);
        member.updateInfo(
                dto.getMbti(),
                dto.getImgUrl(),
                dto.getTripTypes(),
                memberTagCreateService.createMemberTags(member, dto.getFavorites())
        );
    }
}
