package com.tago.api.tripmember.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.tripmember.application.TripMemberDeleteService;
import jakarta.persistence.ColumnResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripMemberDeleteApi {

    private final TripMemberDeleteService tripMemberDeleteService;

    @DeleteMapping("/trips/{tripId}/leave")
    public ResponseEntity<Void> deleteTripMember(
            @PathVariable Long tripId,
            @LoginMember Long memberId
    ){
        tripMemberDeleteService.deleteTripMember(tripId,memberId);
        return ResponseDto.noContent();
    }
}
