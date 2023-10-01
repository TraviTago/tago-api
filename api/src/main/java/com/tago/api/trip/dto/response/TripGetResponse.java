package com.tago.api.trip.dto.response;

import lombok.*;
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
    private Boolean isJoined;
    private List<String> places;
}
