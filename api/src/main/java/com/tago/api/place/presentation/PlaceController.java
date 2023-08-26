package com.tago.api.place.presentation;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.place.application.PlaceService;
import com.tago.api.place.dto.response.PlaceInfoResponse;
import com.tago.domain.place.dto.PlacePreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("places")
    public ResponseEntity<PageResponseDto<PlacePreviewDto>> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("limit") int limit
    ) {
        PageResponseDto<PlacePreviewDto> response = placeService.getAll(cursorId, limit);
        return ResponseDto.ok(response);
    }

    @GetMapping("place/{placeId}")
    public ResponseEntity<PlaceInfoResponse> getPlaceInfo(
            @PathVariable("placeId")Long placeId
    ){
        PlaceInfoResponse response = placeService.getPlaceInfo(placeId);
        return ResponseEntity.ok(response);

    }
}
