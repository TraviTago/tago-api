package com.tago.api.trip.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.TripSaveService;
import com.tago.api.trip.dto.request.TripSaveRequest;
import com.tago.api.trip.dto.response.TripSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripSaveApi {

    private final TripSaveService tripSaveService;

    @PostMapping("/trips")
    public ResponseEntity<TripSaveResponse> create(
            @LoginMember Long memberId,
            @RequestBody TripSaveRequest request
    ) {
        TripSaveResponse response = tripSaveService.create(memberId, request);
        return ResponseDto.ok(response);
    }
}
