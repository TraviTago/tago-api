package com.tago.domain.issue.service;

import com.tago.domain.issue.domain.Issue;
import com.tago.domain.issue.repository.IssueRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IssueCommandService {

    private final IssueRepository issueRepository;

    public Issue save(Issue issue){
        return  issueRepository.save(issue);
    }


}
