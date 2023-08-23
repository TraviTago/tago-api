package com.tago.api.trip.dto.response;

import com.tago.domain.trip.dto.TripPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripGetAllResponse {
    private boolean hasNext;
    private List<TripPreviewDto> trips;
}
