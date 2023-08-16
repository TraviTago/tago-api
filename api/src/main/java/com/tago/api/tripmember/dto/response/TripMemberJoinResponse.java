package com.tago.api.tripmember.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripMemberJoinResponse {
    private Long id;
    private Long trip_id;
    private Long member_id;
}
