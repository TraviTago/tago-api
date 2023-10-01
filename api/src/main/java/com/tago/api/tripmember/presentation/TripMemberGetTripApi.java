package com.tago.api.tripmember.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberGetService;
import com.tago.api.tripmember.dto.response.TripMemberGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripMemberGetTripApi {

    private final TripMemberGetService tripMemberGetTripService;

    @GetMapping("/trips/{tripId}/members")
    public ResponseEntity<TripMemberGetResponse> getMembersByTrip(
            @PathVariable Long tripId
    ) {
        TripMemberGetResponse response = tripMemberGetTripService.getMembersByTrip(tripId);
        return ResponseDto.ok(response);
    }
}
