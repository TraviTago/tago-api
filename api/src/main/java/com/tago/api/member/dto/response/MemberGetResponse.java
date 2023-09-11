package com.tago.api.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberGetResponse {
    private String number;
    private String name;
    private String imgUrl;
    private Integer ageRange;
    private String gender;
    private String mbti;
    private List<String> favorites;
    private List<String> tripTypes;
}





