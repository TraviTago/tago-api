package com.tago.domain.member.domain.vo;

import lombok.Getter;

@Getter
public enum TripType {
    SLOW_LAZY("느긋하고 여유롭게"),
    ;

    private String description;

    private TripType(String description) {
        this.description = description;
    }
}
