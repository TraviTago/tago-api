package com.tago.api.trip.presentation;

import com.tago.api.common.security.annotation.UserAuthentication;
import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.TripGetService;
import com.tago.api.trip.dto.response.MyTripGetResponse;
import com.tago.api.trip.dto.response.TripGetOneResponse;
import com.tago.api.trip.dto.response.TripGetResponse;
import com.tago.api.trip.dto.response.TripStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripGetApi {

    private final TripGetService tripService;

    @GetMapping("/trips")
    public ResponseEntity<PageResponseDto<TripGetResponse>> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit,
            @RequestParam(value = "sameGender", defaultValue = "false") Boolean sameGender,
            @RequestParam(value = "isPet", defaultValue = "false") Boolean isPet,
            @UserAuthentication Long memberId
    ) {
        PageResponseDto<TripGetResponse> response = tripService.getAll(cursorId, cursorDate, limit, sameGender, isPet, memberId);
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<TripGetOneResponse> getOne(
            @UserAuthentication Long memberId,
            @PathVariable Long tripId
    ) {
        TripGetOneResponse response = tripService.getOne(memberId, tripId);
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/{tripId}/status")
    public ResponseEntity<TripStatusResponse> getOneStatus(
            @PathVariable Long tripId
    ) {
        TripStatusResponse tripStatus = tripService.getOneStatus(tripId);
        return ResponseDto.ok(tripStatus);
    }

    @GetMapping("/trips/me")
    public ResponseEntity<MyTripGetResponse> getAllByMember(
            @UserAuthentication Long memberId
    ) {
        MyTripGetResponse response = tripService.getAllByMember(memberId);
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/recommend")
    public ResponseEntity<TripGetResponse> recommend(@UserAuthentication Long memberId) {
        TripGetResponse trip = tripService.recommend(memberId);
        return ResponseDto.ok(trip);
    }

    @GetMapping("/trips/search")
    public ResponseEntity<PageResponseDto<TripGetResponse>> search(
            @RequestParam("keyword") String keyword,
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit,
            @UserAuthentication Long memberId
    ) {
        PageResponseDto<TripGetResponse> response = tripService.search(keyword, cursorId, cursorDate, limit, memberId);
        return ResponseDto.ok(response);
    }
}
