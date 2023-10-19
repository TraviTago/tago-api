package com.tago.domain.trip.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OriginTripCreateDto {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private String meetPlace;
    private int totalTime;
    private int maxCnt;
    private Long memberId;
    private List<Long> places;
    private int currentCnt;
    private Boolean sameGender;
    private Boolean sameAge;
    private Boolean isPet;
    private Boolean origin;

    @Builder
    public OriginTripCreateDto(String name, LocalDateTime dateTime, String meetPlace, int totalTime, int maxCnt, Long memberId, List<Long> places) {
        this.id = 0L;
        this.name = name;
        this.dateTime = dateTime;
        this.meetPlace = meetPlace;
        this.totalTime = totalTime;
        this.maxCnt = maxCnt;
        this.memberId = memberId;
        this.places = places;
        this.currentCnt = 0;
        this.sameGender = false;
        this.sameAge = false;
        this.isPet = false;
        this.origin = true;
    }
}


