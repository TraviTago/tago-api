package com.tago.api.issue.application;


import com.tago.api.infra.slack.application.SlackService;
import com.tago.domain.issue.domain.Issue;
import com.tago.domain.issue.dto.IssueDto;
import com.tago.domain.issue.handler.IssueCommandService;
import com.tago.domain.issue.service.IssueCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueReportService {

    private final IssueCommandService issueCommandService;
    private final IssueCreateService issueCreateService;
    private final SlackService slackService;

    @Transactional
    public Issue reportIssue(Long memberId, IssueDto issueDto){
        Issue issue = issueCreateService.createIssue(memberId, issueDto);
        slackService.sendMessageToSlack(memberId, issueDto);

        return issueCommandService.save(issue);
    }
}


