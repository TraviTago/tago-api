package com.tago.api.tripmember.presentation;


import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripMemberSaveApi {

    private final TripMemberSaveService tripMemberService;

    @PostMapping("/trips/{tripId}/join")
    public ResponseEntity<Void> joinTrip(
            @PathVariable Long tripId,
            @LoginMember Long memberId
    ){
        tripMemberService.joinTrip(tripId, memberId);
        return ResponseDto.noContent();
    }
}



