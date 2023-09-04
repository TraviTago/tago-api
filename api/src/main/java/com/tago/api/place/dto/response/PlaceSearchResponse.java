package com.tago.api.place.dto.response;

import com.tago.domain.place.dto.PlacePreviewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchResponse {

    private List<PlacePreviewDto> places;
}
