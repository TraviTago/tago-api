package com.tago.api.auth.dto.request;

import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.TripType;
import com.tago.domain.member.dto.MemberInfoDto;
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
    private int ageRange;
    private Gender gender;
    private Mbti mbti;
    private List<Favorite> favorites;
    private List<TripType> tripTypes;

    public MemberInfoDto toMemberInfoDto() {
        return MemberInfoDto.builder()
                .ageRange(ageRange)
                .gender(gender)
                .mbti(mbti)
                .favorites(favorites)
                .tripTypes(tripTypes)
                .build();
    }
}
