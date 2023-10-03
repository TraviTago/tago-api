package com.tago.api.driver.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.common.security.annotation.DriverAuthentication;
import com.tago.api.driver.application.DriverDispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/taxi")
public class DriverDispatchApi {

    private final DriverDispatchService driverDispatchService;

    @PostMapping("/trips/{tripId}")
    public ResponseEntity<Void> dispatch(
            @PathVariable("tripId") Long tripId,
            @DriverAuthentication Long driverId,
            @RequestParam("state") String state
    ) {
        driverDispatchService.dispatch(tripId, driverId, state);
        return ResponseDto.noContent();
    }
}
