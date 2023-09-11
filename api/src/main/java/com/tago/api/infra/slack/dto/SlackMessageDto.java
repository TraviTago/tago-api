package com.tago.api.slack.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class SlackMessageDto {
    private String type;
    private String detail;
}
