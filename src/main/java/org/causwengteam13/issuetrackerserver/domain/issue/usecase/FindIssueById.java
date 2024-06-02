package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import lombok.RequiredArgsConstructor;
import org.causwengteam13.issuetrackerserver.domain.issue.command.FindIssueByIdCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.problem.IssueNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.FindIssueByIdResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindIssueById {
    private final IssueRepository issueRepository;

    public FindIssueByIdResult execute(FindIssueByIdCommand command) {
        Issue issue = issueRepository.findById(command.getIssueId())
                .orElseThrow(() -> new IssueNotFoundProblem(command.getIssueId()));

        return new FindIssueByIdResult(issue);
    }
}
