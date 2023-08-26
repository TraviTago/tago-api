package com.tago.api.place.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.place.dto.response.PlaceInfoResponse;
import com.tago.domain.place.domain.Place;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.service.PlaceQueryService;
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


    public PlaceInfoResponse getPlaceInfo(Long placeId){
        Place place = placeQueryService.findById(placeId);
        return mapToPlaceInfoResponse(place);
    }

    private PlaceInfoResponse mapToPlaceInfoResponse(Place place){
        return PlaceInfoResponse.builder()
                .placeId(place.getId().intValue()) // Assuming you want to convert Long to int
                .typeId(String.valueOf(place.getTypeId()))
                .title(place.getTitle())
                .overview(place.getOverview())
                .imageUrl(place.getImgUrl())
                .mapx(place.getMapX())
                .mapy(place.getMapY())
                .address(place.getAddress())
                .homepage(place.getInfo().getHomepage())
                .telephone(place.getInfo().getTelephone())
                .restDate(place.getInfo().getRestDate())
                .openTime(place.getInfo().getOpenTime())
                .parking(place.getInfo().getParking())
                .build();
    }
}
