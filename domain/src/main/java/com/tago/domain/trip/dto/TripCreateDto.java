package com.tago.domain.trip.dto;

import com.tago.domain.member.domain.vo.Favorite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TripCreateDto {
    private String name;
    private LocalDateTime dateTime;
    private int currentCnt;
    private int maxCnt;
    private Boolean sameGender;
    private Boolean sameAge;
    private Boolean isPet;
    private String meetPlace;
    private List<Favorite> types;
    private List<Place> places;

    @Getter
    @AllArgsConstructor
    public static class Place {
        private Long placeId;
        private int order;
    }
}
