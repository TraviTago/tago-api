package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.MemberTag;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.dto.MemberCreateDto;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.service.TagQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final MemberQueryService memberQueryService;
    private final TagQueryHandler tagQueryHandler;

    public Member updateMemberInfo(Long memberId, MemberCreateDto dto) {
        Member member = memberQueryService.findByMemberId(memberId);
        member.updateInfo(
                dto.getAgeRange(),
                dto.getGender(),
                dto.getMbti(),
                dto.getTripTypes(),
                getMemberTags(member, dto.getFavorites())
        );
        return member;
    }

    private List<MemberTag> getMemberTags(Member member, List<Favorite> favorites) {
        return favorites.stream()
                .map(favorite -> getMemberTag(member, favorite))
                .toList();
    }

    private MemberTag getMemberTag(Member member, Favorite favorite) {
        Tag tag = tagQueryHandler.findByType(favorite);
        return MemberTag.builder()
                .member(member)
                .tag(tag)
                .build();
    }
}
