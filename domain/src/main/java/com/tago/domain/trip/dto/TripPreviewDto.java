package com.tago.domain.trip.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripPreviewDto {
    private Long tripId;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String name;
    private int totalTime;
    private int maxMember;
    private int currentMember;
    private List<String> places;

}
