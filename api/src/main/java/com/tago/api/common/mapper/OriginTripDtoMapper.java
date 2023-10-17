package com.tago.api.common.mapper;

import com.tago.api.trip.dto.response.OriginTripGetAllResponse.TagoTripInfo;
import com.tago.api.trip.dto.response.OriginTripGetResponse.TagoTripDetail;
import com.tago.domain.trip.domain.OriginTrip;
import com.tago.domain.trip.domain.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class OriginTripDtoMapper {

    public static List<TagoTripInfo> toOriginTripInfo(List<OriginTrip> trips) {
        return trips.stream()
                .map(OriginTripDtoMapper::getOriginTripInfo)
                .collect(Collectors.toList());
    }

    private static TagoTripInfo getOriginTripInfo(OriginTrip trip) {
        return TagoTripInfo.builder()
                .name(trip.getName())
                .img_url(trip.getImgUrl())
                .source(trip.getSource())
                .build();
    }

    public static List<TagoTripDetail> toOriginTripDetail(List<Trip> trips) {
        return trips.stream()
                .map(OriginTripDtoMapper::getOriginTripDetail)
                .collect(Collectors.toList());
    }

    private static TagoTripDetail getOriginTripDetail(Trip trip) {
        return TagoTripDetail.builder()
                .id(trip.getId())
                .dateTime(trip.getDateTime())
                .maxMember(trip.getMaxCnt())
                .currentMember(trip.getCurrentCnt())
                .build();
    }
}
