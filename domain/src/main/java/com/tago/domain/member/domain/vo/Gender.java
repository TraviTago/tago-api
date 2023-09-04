package com.tago.domain.member.domain.vo;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.exception.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    ;

    private String description;

    private Gender(String description) {
        this.description = description;
    }

    public static Gender from(String gender) {
        return Arrays.stream(Gender.values())
                .filter(value -> value.description.equals(gender))
                .findFirst()
                .orElseThrow(() -> new EnumNotFoundException(ErrorCode.GENDER_NOT_FOUND));
    }

    public boolean isEquals(String gender) {
        return this.description.equals(gender);
    }
}
