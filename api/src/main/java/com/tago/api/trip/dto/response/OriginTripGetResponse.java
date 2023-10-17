package com.tago.api.trip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagoTripGetResponse {
    private String overview;
    private List<TagoTripDetail> tagotrips;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagoTripDetail {
        private Long id;
        private LocalDateTime dateTime;
        private int maxMember;
        private int currentMember;
    }
}

