package com.tago.api.tripmember.presentation;


import com.tago.api.common.security.annotation.UserAuthentication;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberSaveService;
import com.tago.api.tripmember.dto.response.TripMemberGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripMemberApi {

    private final TripMemberSaveService tripMemberService;

    @PostMapping("/trips/{tripId}")
    public ResponseEntity<Void> joinOrLeave(
            @PathVariable Long tripId,
            @RequestParam("state") String state,
            @UserAuthentication Long memberId
    ){
        tripMemberService.joinOrLeave(tripId, memberId, state);
        return ResponseDto.noContent();
    }

    @GetMapping("/trips/{tripId}/members")
    public ResponseEntity<TripMemberGetResponse> getMembersByTrip(
            @PathVariable Long tripId
    ) {
        TripMemberGetResponse response = tripMemberService.getMembersByTrip(tripId);
        return ResponseDto.ok(response);
    }
}



