package com.tago.api.driver.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.common.security.annotation.DriverAuthentication;
import com.tago.api.driver.application.DriverService;
import com.tago.api.driver.dto.response.DriverGetResponse;
import com.tago.domain.driver.dto.DriverInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/taxi")
public class DriverApi {

    private final DriverService driverService;

    @GetMapping("/trips/{tripId}/driver")
    public ResponseEntity<DriverInfoDto> getByTrip(
            @PathVariable("tripId") Long tripId
    ) {
        DriverInfoDto response = driverService.getByTrip(tripId);
        return ResponseDto.ok(response);
    }

    @GetMapping("/drivers/me")
    public ResponseEntity<DriverGetResponse> get(
            @DriverAuthentication Long driverId
    ) {
        DriverGetResponse response = driverService.get(driverId);
        return ResponseDto.ok(response);
    }
}
