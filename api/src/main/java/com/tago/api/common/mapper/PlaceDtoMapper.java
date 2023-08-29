package com.tago.api.common.mapper;

import com.tago.api.place.dto.response.PlaceInfoResponse;
import com.tago.domain.place.domain.Place;

public class PlaceDtoMapper {

    public static PlaceInfoResponse mapToplaceInfoResponse(Place place) {
        return PlaceInfoResponse.builder()
                .placeId(place.getId())
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