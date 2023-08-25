package com.tago.api.tripmember.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberGetTripService;
import com.tago.api.tripmember.dto.response.TripMemberGetTripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripMemberGetTripApi {

    private final TripMemberGetTripService tripMemberGetTripService;

    @GetMapping("/trips/members/me")
    public ResponseEntity<TripMemberGetTripResponse> getTripsByMember(
            @LoginMember Long memberId
    ) {
        TripMemberGetTripResponse response = tripMemberGetTripService.getTripsByMember(memberId);
        return ResponseDto.ok(response);
    }
}
