package com.tago.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto<T> {
    private boolean hasNext;
    private List<T> contents;

    public static <T> PageResponseDto<T> from(List<T> contents) {
        return new PageResponseDto<>(
                !contents.isEmpty(),
                contents
        );
    }
}
