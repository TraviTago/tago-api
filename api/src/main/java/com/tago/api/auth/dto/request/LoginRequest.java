package com.tago.api.auth.dto.request;


import com.tago.domain.member.domain.vo.OAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private OAuthProvider oauthProvider;
    private String email;
    private String name;
    private String imgUrl;
}
