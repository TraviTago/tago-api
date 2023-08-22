package com.tago.api.trip.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.TripService;
import com.tago.api.trip.dto.response.TripGetAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TripController {

    private final TripService tripService;

    @GetMapping("/trips")
    public ResponseEntity<TripGetAllResponse> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit
    ) {
        TripGetAllResponse response = tripService.getAll(cursorId, cursorDate, limit);
        return ResponseDto.ok(response);
    }
}
