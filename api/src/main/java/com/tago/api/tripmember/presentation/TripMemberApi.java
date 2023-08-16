package com.tago.api.tripmember.presentation;


import com.tago.api.auth.jwt.JwtProvider;
import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberService;
import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.trip.domain.TripMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/trips")
public class TripMemberApi {

    @Autowired
    private TripMemberService tripMemberService;

    @PostMapping("/{tripId}/join")
    public ResponseEntity<TripMemberJoinResponse>joinTrip(@PathVariable Long tripId, @LoginMember Long memberId){
        TripMemberJoinResponse response = tripMemberService.joinTrip(tripId,memberId);

        return ResponseDto.ok(response);
    }

}
