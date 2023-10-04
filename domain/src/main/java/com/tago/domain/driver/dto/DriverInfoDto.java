package com.tago.domain.driver.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DriverInfoDto {
    private String img_url;
    private String name;
    private String comment;
    private String phone_number;
    private String licence;
    private int seater;
    private String car_number;

    @QueryProjection
    public DriverInfoDto(String img_url, String name, String comment, String phone_number, String licence, int seater, String car_number) {
        this.img_url = img_url;
        this.name = name;
        this.comment = comment;
        this.phone_number = phone_number;
        this.licence = licence;
        this.seater = seater;
        this.car_number = car_number;
    }
}
