package com.tago.domain.place.repository;

import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.dto.PopularPlaceDto;

import java.util.List;

public interface PlaceCustomRepository {
    List<PlacePreviewDto> findAll(Long cursorId, int limit);

    List<PlacePreviewDto> findByTitleKeyword(String keyword);

    List<PlacePreviewDto> findByPlaceTag(Long memberId);

    List<PopularPlaceDto> findPopularPlaces();


}
