package com.tago.api.tripmember.dto.response;
import com.tago.domain.trip.domain.TripMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripMemberJoinResponse {
    private Long id;
    private Long tripId;
    private Long memberId;

    public static TripMemberJoinResponse from(TripMember tripMember) {
        return new TripMemberJoinResponse(
                tripMember.getId(),
                tripMember.getTripId(),
                tripMember.getMemberId()
        );
    }
}

