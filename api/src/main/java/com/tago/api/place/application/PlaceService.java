package com.tago.api.place.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.mapper.PlaceDtoMapper;
import com.tago.api.place.dto.response.PlaceInfoResponse;
import com.tago.api.place.dto.response.PlaceRecommendResponse;
import com.tago.api.place.dto.response.PlaceSearchResponse;
import com.tago.domain.place.domain.Place;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.handler.PlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceQueryService placeQueryService;

    @Transactional(readOnly = true)
    public PageResponseDto<PlacePreviewDto> getAll(Long cursorId, int limit) {
        List<PlacePreviewDto> places = placeQueryService.getAll(cursorId, limit);
        return PageResponseDto.from(places);
    }

    @Transactional(readOnly = true)
    public PlaceInfoResponse getOne(Long placeId){
        Place place = placeQueryService.findById(placeId);
        return PlaceDtoMapper.mapToplaceInfoResponse(place);
    }

    @Transactional(readOnly = true)
    public PlaceSearchResponse search(String title){
        List<PlacePreviewDto> places = placeQueryService.findByTitleKeyword(title);
        return new PlaceSearchResponse(places);
    }

    @Transactional(readOnly = true)
    public PlaceRecommendResponse recommend(Long memberId){
        List<PlacePreviewDto> places = placeQueryService.findByPlaceTag(memberId);
        return new PlaceRecommendResponse(places);
    }

    public List<PlacePreviewDto> findPopularPlaces(){
        return placeQueryService.findPopularPlaces();
    }

}
