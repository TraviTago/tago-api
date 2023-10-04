package com.tago.api.driver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripGetResponse {
    private Long tripId;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String name;
    private int totalTime;
    private int maxMember;
    private int currentMember;
    private List<String> places;
}
