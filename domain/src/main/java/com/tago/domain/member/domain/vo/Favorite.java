package com.tago.domain.member.domain.vo;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.exception.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Favorite {
    INSTAGRAM("인스타그램"),
    YOUTUBE("유튜브"),
    CAMARA("사진 촬영"),
    HOT_PLACE("핫플")
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
