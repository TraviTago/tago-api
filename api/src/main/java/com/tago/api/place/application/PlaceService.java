package com.tago.api.place.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.service.PlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceQueryService placeQueryService;

    @Transactional(readOnly = true)
    public PageResponseDto<PlacePreviewDto> getAll(Long cursorId, int limit) {
        List<PlacePreviewDto> places = placeQueryService.getAll(cursorId, limit);
        return PageResponseDto.from(places);
    }
}
