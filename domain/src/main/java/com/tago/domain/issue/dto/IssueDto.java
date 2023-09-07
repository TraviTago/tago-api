package com.tago.domain.issue.dto;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {

    private String type;
    private String detail;
}
