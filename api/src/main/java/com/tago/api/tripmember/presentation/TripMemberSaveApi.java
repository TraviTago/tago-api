package com.tago.api.tripmember.presentation;


import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberSaveService;
import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TripMemberApi {

    @Autowired
    private TripMemberSaveService tripMemberService;

    @PostMapping("/trips/{tripId}/join")
    public ResponseEntity<TripMemberJoinResponse> joinTrip(
            @PathVariable Long tripId,
            @LoginMember Long memberId
    ){
        TripMemberJoinResponse response = tripMemberService.joinTrip(tripId, memberId);
        return ResponseDto.ok(response);
    }
}



