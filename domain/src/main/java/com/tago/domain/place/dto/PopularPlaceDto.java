package com.tago.domain.place.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PopularPlaceDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String address;
    private double visit;

    @QueryProjection
    public PopularPlaceDto(Long id,String imageUrl, String title, String address, double visit) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.address = address;
        this.visit = visit;
    }
}
