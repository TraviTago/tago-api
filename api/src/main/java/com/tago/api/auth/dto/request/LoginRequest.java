package com.tago.api.auth.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String sns;
    private String email;
    private String name;
    
}
