package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.MemberTag;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.domain.vo.Profile;
import com.tago.domain.member.dto.MemberCreateDto;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.service.TagQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    public final MemberQueryService memberQueryService;
    public final MemberCommandService memberCommandService;
    private final TagQueryHandler tagQueryHandler;

    public Member create(MemberCreateDto dto) {
        Profile profile = Profile.builder()
                .imgUrl(dto.getImgUrl())
                .ageRange(dto.getAgeRange())
                .gender(dto.getGender())
                .mbti(dto.getMbti())
                .tripTypes(dto.getTripTypes())
                .build();

        Member member = Member.builder()
                .phoneNumber(dto.getNumber())
                .name(dto.getName())
                .authority(Authority.USER)
                .profile(profile)
                .build();

        member.addMemberTags(getMemberTags(member, dto.getFavorites()));

        return memberCommandService.save(member);
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
