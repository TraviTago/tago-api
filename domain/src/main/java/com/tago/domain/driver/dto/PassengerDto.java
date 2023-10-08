package com.tago.domain.driver.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.TripType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PassengerDto {
    private Long memberId;
    private String imgUrl;
    private String phone_number;
    private String name;
    private String mbti;
    private int ageRange;
    private String gender;
    private String tripTypes;

    @QueryProjection
    public PassengerDto(Long memberId, String imgUrl, String phone_number, String name, Mbti mbti,
                         int ageRange, Gender gender, List<TripType> tripTypes
    ) {
        this.memberId = memberId;
        this.imgUrl = imgUrl;
        this.phone_number = phone_number;
        this.name = name;
        this.mbti = mbti.name();
        this.ageRange = ageRange;
        this.gender = gender.getDescription();
        this.tripTypes = TripType.toSentence(tripTypes);
    }
}
