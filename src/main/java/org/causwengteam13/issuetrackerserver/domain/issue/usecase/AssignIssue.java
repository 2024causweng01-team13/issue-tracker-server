package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import org.causwengteam13.issuetrackerserver.domain.issue.command.AssignIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.problem.IssueNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.AssignIssueResult;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignIssue {

	private final IssueRepository issueRepository;
	private final UserRepository userRepository;

	public AssignIssueResult execute(AssignIssueCommand command) {
		Issue issue = issueRepository.findById(command.getIssueId())
			.orElseThrow(() -> new IssueNotFoundProblem(command.getIssueId()));
		User assigner = userRepository.findById(command.getAssignerId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getAssignerId()));
		User assignee = userRepository.findById(command.getAssigneeId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getAssigneeId()));

		issue.assign(assigner, assignee);

		return AssignIssueResult.builder()
			.issueId(issue.getId())
			.assignerName(assigner.getName())
			.assigneeName(issue.getAssignee().getName())
			.status(issue.getStatus())
			.build();
	}
}
