package com.tago.api.place.dto.response;

import com.tago.domain.place.dto.PlacePreviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRecommendResponse {
    List<PlacePreviewDto> places;
}
