package com.tago.api.common.mapper;

import com.tago.api.trip.dto.response.TripGetResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.handler.OriginTripQueryService;
import java.util.List;

public class MyTripDtoMapper {

    public static List<TripGetResponse> toDto(List<Trip> trips, Member member, OriginTripQueryService originTripQueryService) {
        return trips.stream()
                .map(trip -> getTripResponseDto(trip, member, originTripQueryService))
                .toList();
    }

    public static TripGetResponse getTripResponseDto(Trip trip, Member member, OriginTripQueryService originTripQueryService) {
        List<TripPlace> tripPlaces = trip.getTripPlaces();

        return TripGetResponse.builder()
                .tripId(trip.getId())
                .dateTime(trip.getDateTime())
                .imageUrl(getMainPlaceImgUrl(trip, originTripQueryService, tripPlaces))
                .name(trip.getName())
                .totalTime(trip.getTotalTime())
                .maxMember(trip.getMaxCnt())
                .currentMember(trip.getCurrentCnt())
                .isJoined(trip.isJoined(member))
                .places(getPlacesTitle(tripPlaces))
                .build();
    }

    private static String getMainPlaceImgUrl(Trip trip, OriginTripQueryService originTripQueryService, List<TripPlace> tripPlaces) {
        if (trip.isOrigin()) {
            return originTripQueryService.findByName(trip.getName()).getImgUrl();
        }
        return tripPlaces.get(0).getPlace().getImgUrl();
    }

    private static List<String> getPlacesTitle(List<TripPlace> tripPlaces) {
        return tripPlaces.stream()
                .map(TripPlace::getPlaceTitle)
                .toList();
    }
}
