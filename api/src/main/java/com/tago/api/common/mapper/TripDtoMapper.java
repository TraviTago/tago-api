package com.tago.api.common.mapper;

import com.tago.api.trip.dto.response.TripGetResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;

import java.util.List;

public class TripDtoMapper {

    public static List<TripGetResponse> toDto(List<Trip> trips, Member member) {
        return trips.stream()
                .map(trip -> getTripResponseDto(trip, member))
                .toList();
    }

    public static TripGetResponse getTripResponseDto(Trip trip, Member member) {
        List<TripPlace> tripPlaces = trip.getTripPlaces();

        return TripGetResponse.builder()
                .tripId(trip.getId())
                .dateTime(trip.getDateTime())
                .imageUrl(getMainPlaceImgUrl(tripPlaces))
                .name(trip.getName())
                .totalTime(trip.getTotalTime())
                .maxMember(trip.getMaxCnt())
                .currentMember(trip.getCurrentCnt())
                .isJoined(trip.isJoined(member))
                .places(getPlacesTitle(tripPlaces))
                .build();
    }

    private static String getMainPlaceImgUrl(List<TripPlace> tripPlaces) {
        return tripPlaces.get(0).getPlace().getImgUrl();
    }

    private static List<String> getPlacesTitle(List<TripPlace> tripPlaces) {
        return tripPlaces.stream()
                .map(TripPlace::getPlaceTitle)
                .toList();
    }
}
