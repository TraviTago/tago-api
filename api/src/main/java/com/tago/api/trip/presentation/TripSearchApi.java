package com.tago.api.trip.presentation;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.trip.application.TripSearchService;
import com.tago.domain.trip.dto.TripPreviewDto;
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
public class TripSearchApi {

    private final TripSearchService tripSearchService;

    @GetMapping("/trips/search")
    public ResponseEntity<PageResponseDto<TripPreviewDto>> search(
            @RequestParam("keyword") String keyword,
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("cursorDate") LocalDateTime cursorDate,
            @RequestParam("limit") int limit
    ) {
        PageResponseDto<TripPreviewDto> response = tripSearchService.search(keyword, cursorId, cursorDate, limit);
        return ResponseDto.ok(response);
    }
}
