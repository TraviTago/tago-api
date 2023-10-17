package com.tago.api.trip.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.OriginTripGetService;
import com.tago.api.trip.dto.response.OriginTripGetResponse;
import com.tago.api.trip.dto.response.OriginTripGetAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OriginTripGetApi {

    private final OriginTripGetService originTripGetService;


    @GetMapping("/trips/origin")
    public ResponseEntity<OriginTripGetAllResponse> getAll() {
        OriginTripGetAllResponse response = originTripGetService.getAll();
        return ResponseDto.ok(response);
    }

    @GetMapping("/trips/origin/detail")
    public ResponseEntity<OriginTripGetResponse> getByName(
            @RequestParam(name = "name") String name
    ) {
        OriginTripGetResponse tagoTrips = originTripGetService.getByName(name);
        return ResponseDto.ok(tagoTrips);
    }
}
