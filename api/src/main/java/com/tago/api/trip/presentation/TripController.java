package com.tago.api.trip.presentation;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.TripPlaceService;
import com.tago.api.trip.application.TripService;
import com.tago.api.trip.dto.response.TripGetOneResponse;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.trip.dto.TripPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripController {

    private final TripService tripService;
    private final TripPlaceService tripPlaceService;

    @GetMapping("/trips")
    public ResponseEntity<PageResponseDto<TripPreviewDto>> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit
    ) {
        PageResponseDto<TripPreviewDto> response = tripService.getAll(cursorId, cursorDate, limit);
        return ResponseDto.ok(response);
    }


    @GetMapping("trips/{tripId}/status")
    public ResponseEntity<TripStatusResponse> getTripStatus(@PathVariable Long tripId){
        TripStatusResponse tripStatus = tripService.getTripStatus(tripId);
        return ResponseDto.ok(tripStatus);
    }
      
    @GetMapping("/trips/{tripId}")
    public ResponseEntity<TripGetOneResponse> getOne(
            @PathVariable Long tripId
    ) {
        TripGetOneResponse response = tripPlaceService.getTripAndPlaces(tripId);
        return ResponseDto.ok(response);
    }
}
