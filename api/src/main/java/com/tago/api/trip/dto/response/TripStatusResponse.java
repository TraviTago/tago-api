package com.tago.api.trip.dto.response;

import lombok.*;

import java.util.Set;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripStatusResponse {

    private String meetPlace;
    private int femaleCnt;
    private int maleCnt;
    private Set<Integer> ageGroup;
    private String startTime;
    private String endTime;
    private boolean isPet;
}
