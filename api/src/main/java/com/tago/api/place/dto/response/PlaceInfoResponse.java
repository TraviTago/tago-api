package com.tago.api.place.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceInfoResponse {

    private Long id;
    private Long contentId;
    private String typeId;
    private String title;
    private String overview;
    private String imageUrl;
    private double mapX;
    private double mapY;
    private String address;
    private String homepage;
    private String telephone;
    private String restDate;
    private String openTime;
    private String parking;

}
