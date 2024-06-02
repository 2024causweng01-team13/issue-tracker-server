package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import org.causwengteam13.issuetrackerserver.domain.issue.command.EditIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.problem.IssueNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.EditIssueResult;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EditIssue {

	private final UserRepository userRepository;
	private final IssueRepository issueRepository;

	public EditIssueResult execute(EditIssueCommand command) {
		User editor = userRepository.findById(command.getEditorId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getEditorId()));
		Issue issue = issueRepository.findById(command.getIssueId())
			.orElseThrow(() -> new IssueNotFoundProblem(command.getIssueId()));

		issue.edit(editor, command.getTitle(), command.getDescription(), command.getPriority(), command.getStatus());

		return new EditIssueResult(issue.getId());
	}
}
