package com.tago.api.slack.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class SlackMessageDto {
    String title;
    String type;
    String detail;
}
