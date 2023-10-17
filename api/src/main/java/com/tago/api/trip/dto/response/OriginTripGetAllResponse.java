package com.tago.api.trip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagoTripGetAllResponse {
    private List<TagoTripInfo> tagotrips;


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagoTripInfo {
        private String name;
        private String img_url;
        private String source;
    }
}
