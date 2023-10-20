package com.tago.api.trip.dto.request;

import com.tago.domain.tag.domain.vo.TagType;
import com.tago.domain.trip.dto.TripCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripSaveRequest {
    private String name;
    private LocalDateTime dateTime;
    private int currentCnt;
    private int maxCnt;
    private Boolean sameGender;
    private Boolean sameAge;
    private Boolean isPet;
    private String meetPlace;
    private List<String> types;
    private List<Place> places;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
        private Long placeId;
        private int order;
    }

    public TripCreateDto toTripCreateDto() {
        return TripCreateDto.builder()
                .name(name)
                .dateTime(dateTime)
                .currentCnt(0)
                .maxCnt(maxCnt)
                .sameGender(sameGender)
                .sameAge(sameAge)
                .isPet(isPet)
                .meetPlace(meetPlace)
                .types(TagType.from(types))
                .places(toPlaceDto())
                .build();
    }

    private List<TripCreateDto.Place> toPlaceDto() {
        return places.stream()
                .map(place -> new TripCreateDto.Place(place.placeId, place.order))
                .toList();
    }
}
