package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import java.util.ArrayList;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.issue.command.FindIssuesCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.FindIssuesResult;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FindIssues {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public FindIssuesResult execute(FindIssuesCommand command) {
        List<Issue> issues = new ArrayList<>();

        if (command.searchAs() == null) {
            issues = issueRepository.findAll();
            return new FindIssuesResult(issues);
        }

		switch (command.searchAs()) {
			case "PROJECT_ID" -> {
				issues = issueRepository.findByProjectId(command.projectId());
				return new FindIssuesResult(issues);
			}
			case "ASSIGNEE_NAME" -> {
				issues = issueRepository.findByAssigneeName(command.assigneeName());
				return new FindIssuesResult(issues);
			}
			case "REPORTER_NAME" -> {
				issues = issueRepository.findByReporterName(command.reporterName());
				return new FindIssuesResult(issues);
			}
			default -> {
				//STATUS
				issues = issueRepository.findByStatus(IssueStatus.valueOf(command.status()));
				return new FindIssuesResult(issues);
			}
		}
    }
}
