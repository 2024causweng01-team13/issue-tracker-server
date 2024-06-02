package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import org.causwengteam13.issuetrackerserver.domain.issue.command.FixIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.problem.IssueNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.FixIssueResult;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FixIssue {

	private final IssueRepository issueRepository;
	private final UserRepository userRepository;

	public FixIssueResult execute(FixIssueCommand command) {
		Issue issue = issueRepository.findById(command.getIssueId())
			.orElseThrow(() -> new IssueNotFoundProblem(command.getIssueId()));
		User fixer = userRepository.findById(command.getFixerId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getFixerId()));

		issue.fix(fixer, command.getComment());

		return FixIssueResult.builder()
			.issueId(issue.getId())
			.fixerName(issue.getFixer().getName())
			.status(issue.getStatus())
			.updatedAt(issue.getUpdatedAt())
			.build();
	}
}
