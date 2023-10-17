package com.tago.api.member.dto.request;

import com.tago.domain.tag.domain.vo.TagType;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.TripType;
import com.tago.domain.member.dto.MemberUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequest {
    private String imgUrl;
    private String mbti;
    private List<String> favorites;
    private List<String> tripTypes;

    public MemberUpdateDto toMemberUpdateDto() {
        return MemberUpdateDto.builder()
                .imgUrl(imgUrl)
                .mbti(Mbti.valueOf(mbti))
                .tagTypes(TagType.from(favorites))
                .tripTypes(TripType.from(tripTypes))
                .build();
    }
}
