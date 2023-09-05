package com.tago.api.trip.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripSearchService {

    private final TripQueryService tripQueryService;

    @Transactional(readOnly = true)
    public PageResponseDto<TripPreviewDto> search(String keyword, Long cursorId, LocalDateTime cursorDate, int limit) {
        List<TripPreviewDto> trips = tripQueryService.searchByPlaceTitleKeyword(keyword, cursorId, cursorDate, limit);
        return PageResponseDto.from(trips);
    }
}
