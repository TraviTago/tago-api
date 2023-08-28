package com.tago.domain.tripmember.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.TripType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TripMemberDto {
    private Long memberId;
    private String imgUrl;
    private String name;
    private String mbti;
    private int ageRange;
    private String gender;
    private String tripTypes;

    @QueryProjection
    public TripMemberDto(Long memberId, String imgUrl, String name, Mbti mbti,
            int ageRange, Gender gender, List<TripType> tripTypes
    ) {
        this.memberId = memberId;
        this.imgUrl = imgUrl;
        this.name = name;
        this.mbti = mbti.name();
        this.ageRange = ageRange;
        this.gender = gender.name();
        this.tripTypes = TripType.toSentence(tripTypes);
    }
}
