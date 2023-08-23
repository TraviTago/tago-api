package com.tago.domain.member.domain.vo;

import lombok.Getter;

import java.util.List;

@Getter
public enum TripType {
    SLOW_LAZY("느긋하고 여유롭게"),
    ;

    private String description;

    private TripType(String description) {
        this.description = description;
    }

    public static List<TripType> from(List<String> tripTypes) {
        return tripTypes.stream()
                .map(TripType::valueOf)
                .toList();
    }
}
