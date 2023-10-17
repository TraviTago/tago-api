package com.tago.api.common.mapper;

import com.tago.api.member.dto.response.MemberGetResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.tag.domain.vo.TagType;
import com.tago.domain.member.domain.vo.TripType;

public class MemberDtoMapper {

    public static MemberGetResponse toDto(Member member) {
        return new MemberGetResponse(
                member.getPhoneNumber(),
                member.getName(),
                member.getImgUrl(),
                member.getAgeRange(),
                member.getGender().name(),
                member.getMbti().name(),
                TagType.toString(member.getMemberTags()),
                TripType.toString(member.getTripTypes())
        );
    }
}
