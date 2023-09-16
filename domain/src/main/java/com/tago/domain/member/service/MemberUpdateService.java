package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.MemberTag;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.dto.MemberCreateDto;
import com.tago.domain.member.dto.MemberUpdateDto;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.service.TagQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final MemberQueryService memberQueryService;
    private final MemberTagCreateService memberTagCreateService;

    public void updateMemberInfo(Long memberId, MemberUpdateDto dto) {
        Member member = memberQueryService.findByMemberId(memberId);
        member.updateInfo(
                dto.getMbti(),
                dto.getImgUrl(),
                dto.getTripTypes(),
                memberTagCreateService.createMemberTags(member, dto.getFavorites())
        );
    }
}
