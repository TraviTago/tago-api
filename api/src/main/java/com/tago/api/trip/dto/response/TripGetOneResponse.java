package com.tago.api.trip.dto.response;

import com.tago.domain.trip.dto.TripPlaceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripGetOneResponse {
    private String tripName;
    private int currentCnt;
    private int maxCnt;
    private Boolean isJoined;
    private List<TripPlaceDto> places;
}
