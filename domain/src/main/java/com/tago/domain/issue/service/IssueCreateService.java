package com.tago.domain.issue.service;

import com.tago.domain.issue.domain.Issue;
import com.tago.domain.issue.domain.vo.IssueType;
import com.tago.domain.issue.dto.IssueDto;
import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.service.TripMemberCommandService;
import lombok.NoArgsConstructor;
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
