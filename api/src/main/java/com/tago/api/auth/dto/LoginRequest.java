package com.tago.api.auth.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LoginRequest {

    private String sns;
    private String email;
}
