package com.tago.domain.trip.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class TripRecommendDto {
    private Long tripId;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String name;
    private int totalTime;
    private int maxMember;
    private int currentMember;
    private List<String> places;

    @QueryProjection
    public TripRecommendDto(Long tripId, LocalDateTime dateTime, String imageUrl, String name,
                            int totalTime, int maxMember, int currentMember, List<String> places) {
        this.tripId = tripId;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.name = name;
        this.totalTime = totalTime;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.places = places;

    }
}
