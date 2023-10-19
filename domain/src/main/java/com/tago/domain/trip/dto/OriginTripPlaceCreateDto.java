package com.tago.domain.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OriginTripPlaceCreateDto {
    private int order;
    private Long tripId;
    private Long placeId;
}
