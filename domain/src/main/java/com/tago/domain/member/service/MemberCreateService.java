package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.Profile;
import com.tago.domain.member.dto.MemberCreateDto;
import com.tago.domain.member.handler.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberCommandService memberCommandService;
    private final MemberTagCreateService memberTagCreateService;

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

        member.addMemberTags(memberTagCreateService.createMemberTags(member, dto.getFavorites()));

        return memberCommandService.save(member);
    }
}
