package com.tago.api.driver.presentation;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.common.security.annotation.DriverAuthentication;
import com.tago.api.driver.application.DriverTripService;
import com.tago.api.driver.dto.response.DriverTripGetAllResponse;
import com.tago.api.driver.dto.response.TripGetOneResponse;
import com.tago.api.driver.dto.response.TripGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/taxi")
public class DriverTripApi {

    private final DriverTripService driverTripService;

    @PostMapping("/trips/{tripId}")
    public ResponseEntity<Void> dispatch(
            @PathVariable("tripId") Long tripId,
            @DriverAuthentication Long driverId,
            @RequestParam("state") String state
    ) {
        driverTripService.dispatch(tripId, driverId, state);
        return ResponseDto.noContent();
    }

    @GetMapping("/trips")
    public ResponseEntity<PageResponseDto<TripGetResponse>> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit
    ) {
        PageResponseDto<TripGetResponse> response = driverTripService.getAll(cursorId, cursorDate, limit);
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<TripGetOneResponse> getOne(
            @PathVariable("tripId") Long tripId,
            @DriverAuthentication Long driverId
    ) {
        TripGetOneResponse response = driverTripService.getOne(tripId, driverId);
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/me")
    public ResponseEntity<DriverTripGetAllResponse> getAllByDriver(
            @DriverAuthentication Long driverId
    ) {
        DriverTripGetAllResponse response = driverTripService.getAllByDriver(driverId);
        return ResponseDto.ok(response);
    }
}
