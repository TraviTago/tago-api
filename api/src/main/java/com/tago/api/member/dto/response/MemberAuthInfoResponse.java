package com.tago.api.member.dto.response;

import com.tago.domain.member.domain.vo.OAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAuthInfoResponse {
    private String email;
    private OAuthProvider oAuthProvider;
    private boolean isSignedUp;
}





