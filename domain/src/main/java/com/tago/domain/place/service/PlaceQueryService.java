package com.tago.domain.place.service;

import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceQueryService {

    private final PlaceRepository placeRepository;

    public List<PlacePreviewDto> getAll(Long cursorId, int limit) {
        return placeRepository.findAll(cursorId, limit);
    }
}