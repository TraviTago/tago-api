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

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private int ageRange;
    private String gender;
    private Mbti mbti;
    private List<String> favorites;
    private List<String> tripTypes;

    public MemberInfoDto toMemberInfoDto() {
        return MemberInfoDto.builder()
                .ageRange(ageRange)
                .gender(Gender.valueOf(gender))
                .mbti(mbti)
                .favorites(Favorite.from(favorites))
                .tripTypes(TripType.from(tripTypes))
                .build();
    }
}
