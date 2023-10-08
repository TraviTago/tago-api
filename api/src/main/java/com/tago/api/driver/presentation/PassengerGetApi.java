package com.tago.api.driver.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.driver.application.PassengerGetService;
import com.tago.api.driver.dto.response.PassengerGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PassengerGetApi {

    private final PassengerGetService passengerGetService;

    @GetMapping("/taxi/trips/{tripId}/members")
    public ResponseEntity<PassengerGetResponse> getPassengerByTrip(
            @PathVariable Long tripId
    ) {
        PassengerGetResponse response = passengerGetService.getPassengerByTip(tripId);
        return ResponseDto.ok(response);
    }
}