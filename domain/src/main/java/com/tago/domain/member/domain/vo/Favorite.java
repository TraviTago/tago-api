package com.tago.domain.member.domain.vo;

import lombok.Getter;

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

    public static List<Favorite> from(List<String> favorites) {
        return favorites.stream()
                .map(Favorite::valueOf)
                .toList();
    }
}
