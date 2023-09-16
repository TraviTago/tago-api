package com.tago.domain.trip.mapper;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.dto.TripRecommendDto;

import java.util.List;

public class TripDtoMapper {

    public static List<TripPreviewDto> toTripPreviews(List<Trip> trips) {
        return trips.stream()
                .map(TripDtoMapper::getTripPreviewDto)
                .toList();
    }

    private static TripPreviewDto getTripPreviewDto(Trip trip) {
        List<TripPlace> tripPlaces = trip.getTripPlaces();

        return TripPreviewDto.builder()
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

    public static TripRecommendDto toTripRecommendDto(Trip trip) {
        List<TripPlace> tripPlaces = trip.getTripPlaces();

        return TripRecommendDto.builder()
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

}
