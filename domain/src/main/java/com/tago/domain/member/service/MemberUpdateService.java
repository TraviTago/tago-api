package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final MemberQueryService memberQueryService;

    public Member updateMemberInfo(Long memberId, MemberInfoDto dto) {
        Member member = memberQueryService.findById(memberId);
        member.updateInfo(
                dto.getAgeRange(),
                dto.getGender(),
                dto.getMbti(),
                dto.getFavorites(),
                dto.getTripTypes()
        );
        return member;
    }
}
