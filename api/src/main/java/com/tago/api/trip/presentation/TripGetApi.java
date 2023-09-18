package com.tago.api.trip.presentation;

import ch.qos.logback.core.joran.event.BodyEvent;
import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.TripGetService;
import com.tago.api.trip.dto.response.TripGetOneResponse;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.dto.TripRecommendDto;
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
    public ResponseEntity<PageResponseDto<TripPreviewDto>> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit,
            @RequestParam(value="sameGender", defaultValue = "false")Boolean sameGender,
            @RequestParam(value = "isPet", defaultValue = "false") Boolean isPet
            ) {
    PageResponseDto<TripPreviewDto> response = tripService.getAll(cursorId, cursorDate, limit, sameGender, isPet);
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<TripGetOneResponse> getOne(
            @LoginMember Long memberId,
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

    @GetMapping("/trips/recommend")
    public ResponseEntity<TripRecommendDto> getByTripTag(@LoginMember Long memberId) {
        TripRecommendDto trip = tripService.findByTripTag(memberId);
        return ResponseEntity.ok(trip);
    }
}
