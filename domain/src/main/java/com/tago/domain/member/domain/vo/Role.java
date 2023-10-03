package com.tago.domain.member.domain.vo;

import lombok.Getter;

@Getter
public enum Role {
    USER("ROLE_USER"),
    DRIVER("ROLE_DRIVER"),
    ADMIN("ROLE_ADMIN"),
    ;

    private final String role;
    private Role(String role) {
        this.role = role;
    }
}
