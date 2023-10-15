package com.tago.api.auth.dto.request;


import com.tago.api.common.annotation.PhoneNumberPattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @PhoneNumberPattern
    private String number;
    private String firebaseToken;
}
