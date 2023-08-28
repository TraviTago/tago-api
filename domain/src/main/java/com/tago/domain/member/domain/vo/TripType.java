package com.tago.domain.member.domain.vo;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.exception.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum TripType {
    SLOW_LAZY("느긋하고 여유롭게", "저는 느긋하고 여유롭게 여행하는 걸 좋아해요!"),
    DILIGENT("부지런히 이곳저곳!", "저는 부지런히 이곳저곳 여행하는 걸 좋아해요!"),

    IMPORTANT_FOOD("여행에서 음식은 중요해", "음식을 중요하게 생각하고"),
    NOT_IMPORTANT_FOOD("음식은 크게 중요하지 않아", "음식은 별로 중요하지 않고"),

    LATEST_TREND("최신유행은 가봐야지", "최신유행하는게 좋아요."),
    NOT_MANY_PEOPLE("사람이 많지않은 좋은 곳", "사람이 많지 않은 곳이 좋아요."),
    ;

    private String description;
    private String sentence;

    private TripType(String description, String sentence) {
        this.description = description;
        this.sentence = sentence;
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

    public static String toSentence(List<TripType> tripTypes) {
        List<String> sentences = tripTypes.stream()
                    .map(tripType -> tripType.sentence)
                    .toList();
        return String.join(" ", sentences);
    }
}
