package com.tago.api.issue.application;


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

    @Transactional
    public Issue reportIssue(Long memberId, IssueDto issueDto){
        Issue issue = issueCreateService.createIssue(memberId, issueDto);

        return issueCommandService.save(issue);
    }
}

