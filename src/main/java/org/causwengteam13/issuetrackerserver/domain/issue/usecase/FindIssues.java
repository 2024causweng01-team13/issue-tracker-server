package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import lombok.RequiredArgsConstructor;
import org.causwengteam13.issuetrackerserver.domain.issue.command.FindIssuesCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.FindIssuesResult;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.h2.command.Command;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindIssues {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public FindIssuesResult execute(FindIssuesCommand command) {
        List<Issue> issues = new ArrayList<>();


        if(command.searchAs().equals("PROJECT_ID")) {
            issues = issueRepository.findByProjectId(command.projectId());
            return new FindIssuesResult(issues);


        } else if(command.searchAs().equals("ASSIGNEE_NAME")) {
            issues = issueRepository.findByAssigneeName(command.assigneeName());
            return new FindIssuesResult(issues);


        } else if(command.searchAs().equals("REPORTER_NAME")) {
            issues = issueRepository.findByReporterName(command.reporterName());
            return new FindIssuesResult(issues);


        } else {
            //STATUS
            issues = issueRepository.findByStatus(IssueStatus.valueOf(command.status()));
            return new FindIssuesResult(issues);
        }
    }
}
