package com.tago.domain.member.domain.vo;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.exception.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum TripType {
    SLOW_LAZY("느긋하고 여유롭게"),
    DILIGENT("부지런히 이곳저곳!"),
    LATEST_TREND("최신유행은 가봐야지")
    ;

    private String description;

    private TripType(String description) {
        this.description = description;
    }

    public static List<TripType> from(List<String> tripTypes) {
        return tripTypes.stream()
                .map(TripType::of)
                .toList();
    }

    private static TripType of(String tripType) {
        return Arrays.stream(TripType.values())
                .filter(value -> value.description.equals(tripType))
                .findFirst()
                .orElseThrow(() -> new EnumNotFoundException(ErrorCode.TRIP_TYPE_NOT_FOUND));
    }

    public static List<String> toString(List<TripType> tripTypes) {
        return tripTypes.stream()
                .map(TripType::getDescription)
                .toList();
    }
}
