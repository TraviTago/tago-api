package com.tago.domain.tripmember.mapper;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.tripmember.dto.MyTripDto;

import java.util.List;

public class MyTripDtoMapper {

    public static List<MyTripDto> toMyTripDto(List<Trip> trips) {
        return trips.stream()
                .map(MyTripDtoMapper::getMyTripDto)
                .toList();
    }

    private static MyTripDto getMyTripDto(Trip trip) {
        List<TripPlace> tripPlaces = trip.getTripPlaces();

        return MyTripDto.builder()
                .tripId(trip.getId())
                .dateTime(trip.getDateTime())
                .imageUrl(getMainPlaceImgUrl(tripPlaces))
                .name(trip.getName())
                .totalTime(trip.getTotalTime())
                .maxMember(trip.getMaxCnt())
                .currentMember(trip.getCurrentCnt())
                .places(getPlacesName(tripPlaces))
                .build();
    }

    private static String getMainPlaceImgUrl(List<TripPlace> tripPlaces) {
        return tripPlaces.get(0).getPlace().getImgUrl();
    }

    private static List<String> getPlacesName(List<TripPlace> tripPlaces) {
        return tripPlaces.stream()
                .map(TripPlace::getPlaceName)
                .toList();
    }
}
