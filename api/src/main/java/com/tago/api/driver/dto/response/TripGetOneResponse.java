package com.tago.api.driver.dto.response;

import com.tago.domain.trip.dto.TripPlaceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripGetOneResponse {
    private String tripName;
    private int currentCnt;
    private int maxCnt;
    private Boolean isDispatched;
    private List<TripPlaceDto> places;
}
