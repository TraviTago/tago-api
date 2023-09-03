package com.tago.domain.issue.domain.vo;

import lombok.Getter;

@Getter
public enum IssueType {

    TRAVEL_MATE_ISSUE("여행 메이트와 문제발생"),
    TAXI_DRIVER_ISSUE("택시 기사 문제"),
    PLAN_ISSUE("계획 틀어짐"),
    OTHERS("기타")
    ;

    private String description;

    private IssueType(String description) {this.description = description; }

    public String getDescription() {
        return description;
    }


}
