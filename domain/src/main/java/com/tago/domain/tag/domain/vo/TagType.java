package com.tago.domain.tag.domain.vo;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.exception.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum TagType {
    CAMERA("사진촬영"),
    HOT_PLACE("핫플"),
    FOOD("맛집탐방"),
    STROLL("산책"),
    ART("미술/예술"),
    BOOK("독서"),
    MOVIE("영화"),
    ACTIVITY("레저/액티비티"),
    ANIMAL("동물"),
    NATURE("자연"),
    HISTORY("역사"),
    MARKET("전통시장"),
    BEACH("바다"),
    FESTIVAL("지역축제"),
    CAFE("카페"),
    SHOPPING("쇼핑"),

    MANAGE("관리")
    ;

    private String description;

    private TagType(String description) {
        this.description = description;
    }
;
    public static List<TagType> from(List<String> types) {
        return types.stream()
                .map(TagType::of)
                .toList();
    }

    private static TagType of(String type) {
        return Arrays.stream(TagType.values())
                .filter(value -> value.description.equals(type))
                .findFirst()
                .orElseThrow(() -> new EnumNotFoundException(ErrorCode.FAVORITE_NOT_FOUND));
    }

    public static List<String> toString(List<TagType> tagTypes) {
        return tagTypes.stream()
                .map(TagType::getDescription)
                .toList();
    }
}
