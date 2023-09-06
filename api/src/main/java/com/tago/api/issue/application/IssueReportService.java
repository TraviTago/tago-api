package com.tago.api.issue.application;


import com.tago.api.slack.application.SlackService;
import com.tago.api.slack.dto.SlackMessageDto;
import com.tago.domain.issue.domain.Issue;
import com.tago.domain.issue.domain.vo.IssueType;
import com.tago.domain.issue.dto.IssueDto;
import com.tago.domain.issue.service.IssueCommandService;
import com.tago.domain.issue.service.IssueCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueReportService {

    private final IssueCommandService issueCommandService;
    private final IssueCreateService issueCreateService;
    private final SlackService slackService; // 이 부분 추가

    @Transactional
    public Issue reportIssue(Long memberId, IssueDto issueDto){
        Issue issue = issueCreateService.createIssue(memberId, issueDto);

        SlackMessageDto slackMessageDto = SlackMessageDto.builder()
                .title("불편신고")
                .type(issue.getType().toString())
                .detail(issue.getDetail())
                .build();

        slackService.sendMessageToSlack(slackMessageDto);

        return issueCommandService.save(issue);
    }
}


