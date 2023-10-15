package com.tago.api.trip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TagoTripResponse {
    private List<TagotripDTO> tagotrips;

    @Data
    @AllArgsConstructor
    public static class TagotripDTO {
        private String name;
        private String img_url;
        private String source;
    }
}
