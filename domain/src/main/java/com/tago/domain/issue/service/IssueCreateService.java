package com.tago.domain.issue.service;

import com.tago.domain.issue.domain.Issue;
import com.tago.domain.issue.domain.vo.IssueType;
import com.tago.domain.issue.dto.IssueDto;
import com.tago.domain.issue.handler.IssueCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueCreateService {

    public final IssueCommandService issueCommandService;

    public Issue createIssue(Long memberId, IssueDto issueDto) {
        return Issue.builder()
                .memberId(memberId)
                .type(IssueType.valueOf(issueDto.getType()))
                .detail(issueDto.getDetail())
                .build();
    }

}
