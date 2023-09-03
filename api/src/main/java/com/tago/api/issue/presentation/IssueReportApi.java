package com.tago.api.issue.presentation;

import com.tago.api.common.annotation.LoginMember;
import com.tago.api.issue.application.IssueReportService;
import com.tago.domain.issue.domain.Issue;
import com.tago.domain.issue.dto.IssueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class IssueReportApi {

    public final IssueReportService issueReportService;

    @PostMapping("/report")
    public Issue reportIssue(@LoginMember Long memberId, @RequestBody IssueDto issueDto) {
        return issueReportService.reportIssue(memberId, issueDto);
    }

}
