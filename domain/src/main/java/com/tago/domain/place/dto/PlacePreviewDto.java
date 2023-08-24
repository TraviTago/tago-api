package com.tago.domain.place.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlacePreviewDto {
    private String imageUrl;
    private String title;
    private String address;

    @QueryProjection
    public PlacePreviewDto(String imageUrl, String title, String address) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.address = address;
    }
}
