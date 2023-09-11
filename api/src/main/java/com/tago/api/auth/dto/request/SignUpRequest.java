package com.tago.api.auth.dto.request;

import com.tago.api.common.annotation.PhoneNumberPattern;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.TripType;
import com.tago.domain.member.dto.MemberCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @PhoneNumberPattern
    private String number;
    private String name;
    private String imgUrl;
    private int ageRange;
    private String gender;
    private String mbti;
    private List<String> favorites;
    private List<String> tripTypes;

    public MemberCreateDto toMemberInfoDto() {
        return MemberCreateDto.builder()
                .number(number)
                .name(name)
                .imgUrl(imgUrl)
                .ageRange(ageRange)
                .gender(Gender.from(gender))
                .mbti(Mbti.valueOf(mbti))
                .favorites(Favorite.from(favorites))
                .tripTypes(TripType.from(tripTypes))
                .build();
    }
}
