package com.tago.domain.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OriginTripCreateDto {
    private String name;
    private LocalDateTime dateTime;
    private String meetPlace;
    private int totalTime;
    private int maxCnt;
    private Long memberId;

    @Default
    private int currentCnt = 0;
    @Default
    private Boolean sameGender = false;
    @Default
    private Boolean sameAge = false;
    @Default
    private Boolean isPet = false;
    @Default
    private Boolean origin = true;
}


