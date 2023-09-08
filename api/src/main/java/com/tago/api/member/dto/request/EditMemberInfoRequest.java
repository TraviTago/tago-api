package com.tago.api.member.dto.request;

import com.tago.domain.member.domain.vo.Favorite;
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
public class EditMemberInfoRequest {
    private Mbti mbti;
    private List<Favorite> favorites;
    private List<TripType> tripTypes;
}