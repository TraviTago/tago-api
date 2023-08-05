package com.tago.domain.member.domain.vo;

import lombok.Getter;

@Getter
public enum Authority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ;

    private final String role;
    private Authority(String role) {
        this.role = role;
    }
}
