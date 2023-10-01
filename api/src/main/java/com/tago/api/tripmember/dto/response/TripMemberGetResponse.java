package com.tago.api.tripmember.dto.response;

import com.tago.domain.tripmember.dto.TripMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripMemberGetResponse {
    private List<TripMemberDto> members;
}
