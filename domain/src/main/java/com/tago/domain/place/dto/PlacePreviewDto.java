package com.tago.domain.place.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlacePreviewDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String address;

    @QueryProjection
    public PlacePreviewDto(Long id,String imageUrl, String title, String address) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.address = address;
    }
}
