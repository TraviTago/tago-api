package com.tago.api.common.mapper;

import com.tago.api.driver.dto.response.TripGetResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;

import java.util.List;

public class DriverTripDtoMapper {

    public static List<TripGetResponse> toDto(List<Trip> trips) {
        return trips.stream()
                .map(DriverTripDtoMapper::getTripResponseDto)
                .toList();
    }

    public static TripGetResponse getTripResponseDto(Trip trip) {
        List<TripPlace> tripPlaces = trip.getTripPlaces();

        return TripGetResponse.builder()
                .tripId(trip.getId())
                .dateTime(trip.getDateTime())
                .imageUrl(getMainPlaceImgUrl(tripPlaces))
                .name(trip.getName())
                .totalTime(trip.getTotalTime())
                .maxMember(trip.getMaxCnt())
                .currentMember(trip.getCurrentCnt())
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
