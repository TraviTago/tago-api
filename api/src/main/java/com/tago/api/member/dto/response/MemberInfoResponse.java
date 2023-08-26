package com.tago.api.member.dto.response;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.member.domain.vo.TripType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResponse {
    private String email;
    private String oauthProvider;
    private String name;
    private String imgUrl;
    private Profile profile;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Profile {
        private Integer ageRange;
        private String gender;
        private String mbti;
        private List<String> favorites;
        private List<String> tripTypes;
    }
}





