package com.tago.api.place.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.dto.ResponseDto;
import com.tago.api.place.application.PlaceService;
import com.tago.api.place.dto.response.PlaceInfoResponse;
import com.tago.api.place.dto.response.PlaceRecommendResponse;
import com.tago.api.place.dto.response.PlaceSearchResponse;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.place.dto.PlacePreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlaceApi {

    private final PlaceService placeService;

    @GetMapping("/places")
    public ResponseEntity<PageResponseDto<PlacePreviewDto>> getAll(
            @RequestParam("cursorId") Long cursorId,
            @RequestParam("limit") int limit
    ) {
        PageResponseDto<PlacePreviewDto> response = placeService.getAll(cursorId, limit);
        return ResponseDto.ok(response);
    }

    @GetMapping("/place/{placeId}")
    public ResponseEntity<PlaceInfoResponse> getOne(
            @PathVariable("placeId")Long placeId
    ){
        PlaceInfoResponse response = placeService.getOne(placeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/places/search")
    public ResponseEntity<PlaceSearchResponse> search(
            @RequestParam("keyword") String keyword
    ){
        PlaceSearchResponse response = placeService.search(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/places/recommend")
    public ResponseEntity<PlaceRecommendResponse> recommend(
            @LoginMember Long memberId
    ) {
        PlaceRecommendResponse Places = placeService.recommend(memberId);
        return ResponseEntity.ok(Places);
    }
}
