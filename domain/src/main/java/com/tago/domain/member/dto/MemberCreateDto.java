package com.tago.domain.member.dto;

import com.tago.domain.tag.domain.vo.TagType;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.TripType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateDto {
    private String number;
    private String name;
    private String imgUrl;
    private int ageRange;
    private Gender gender;
    private Mbti mbti;
    private List<TagType> tagTypes;
    private List<TripType> tripTypes;
}
