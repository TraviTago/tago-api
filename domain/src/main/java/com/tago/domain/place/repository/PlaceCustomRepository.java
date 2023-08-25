package com.tago.domain.place.repository;

import com.tago.domain.place.dto.PlacePreviewDto;

import java.util.List;

public interface PlaceCustomRepository {
    List<PlacePreviewDto> findAll(Long cursorId, int limit);
}