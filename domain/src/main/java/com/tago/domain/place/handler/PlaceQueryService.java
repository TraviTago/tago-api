package com.tago.domain.place.handler;

import com.tago.domain.place.domain.Place;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.exception.PlaceNotFoundException;
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

    public List<PlacePreviewDto> findByTitleKeyword(String title) {
        return placeRepository.findByTitleKeyword(title);
    }

    public Place findById(Long placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(PlaceNotFoundException::new);
    }

    public Place findByTitle(String title){
        return placeRepository.findByTitle(title).orElse(null);
    }

    public List<PlacePreviewDto> findByPlaceTag(Long memberId){
        return placeRepository.findByPlaceTag(memberId);
    }

    public List<PlacePreviewDto> findPopularPlaces(){
        return placeRepository.findPopularPlaces();
    }
}
