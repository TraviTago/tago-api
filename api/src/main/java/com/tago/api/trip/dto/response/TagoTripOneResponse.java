package com.tago.api.trip.dto.response;

import com.tago.domain.trip.dto.TagoTripOneDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TagoTripOneResponse {
    private List<TagoTripOneDto> tagotrips;
}

