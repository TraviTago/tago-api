package com.tago.domain.member.domain.vo;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.exception.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Favorite {
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

    ;

    private String description;

    private Favorite(String description) {
        this.description = description;
    }
;
    public static List<Favorite> from(List<String> favorites) {
        return favorites.stream()
                .map(Favorite::of)
                .toList();
    }

    private static Favorite of(String favorite) {
        return Arrays.stream(Favorite.values())
                .filter(value -> value.description.equals(favorite))
                .findFirst()
                .orElseThrow(() -> new EnumNotFoundException(ErrorCode.FAVORITE_NOT_FOUND));
    }

    public static List<String> toString(List<Favorite> favorites) {
        return favorites.stream()
                .map(Favorite::getDescription)
                .toList();
    }
}
