package com.tago.api.tripmember.presentation;


import com.tago.api.auth.jwt.JwtProvider;
import com.tago.api.tripmember.application.TripMemberService;
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

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/{trip_id}/join")
    public ResponseEntity<Map<String, Long>> joinTrip(@PathVariable Long trip_id, @RequestHeader("accesss-Token") String accessToken) {
        Long member_id = extractMemberIdFromToken(accessToken);

        TripMember tripMember = tripMemberService.joinTrip(trip_id, member_id);

        Map<String, Long> response = new HashMap<>();
        response.put("id", tripMember.getId());
        response.put("trip_id", tripMember.getTrip_id());
        response.put("member_id", tripMember.getMember_id());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Long extractMemberIdFromToken(String accessToken) {
        Authentication auth = jwtProvider.authenticate(accessToken);
        return (Long) auth.getPrincipal();
    }
}
