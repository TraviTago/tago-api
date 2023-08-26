package com.tago.api.common.mapper;

import com.tago.api.member.dto.response.MemberInfoResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.domain.vo.TripType;

public class MemberDtoMapper {

    public static MemberInfoResponse toDto(Member member) {
        return new MemberInfoResponse(
                member.getEmail(),
                member.getOauthProvider().name(),
                member.getName(),
                member.getImgUrl(),
                getProfile(member)
        );
    }

    private static MemberInfoResponse.Profile getProfile(Member member) {
        if (!member.isSignUp()) return null;

        return new MemberInfoResponse.Profile(
                member.getAgeRange(),
                member.getGender().name(),
                member.getMbti().name(),
                Favorite.toString(member.getFavorites()),
                TripType.toString(member.getTripTypes())
        );
    }
}
