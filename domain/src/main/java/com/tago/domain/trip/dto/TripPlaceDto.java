package com.tago.domain.trip.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TripPlaceDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String overview;
    private double mapX;
    private double mapY;

    @QueryProjection
    public TripPlaceDto(Long id, String title, String imageUrl, String overview, double mapX, double mapY) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.overview = overview;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
