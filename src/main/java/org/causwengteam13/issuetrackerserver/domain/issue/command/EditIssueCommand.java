package org.causwengteam13.issuetrackerserver.domain.issue.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EditIssueCommand extends SelfValidator<EditIssueCommand> {

	@NotNull
	private final Long issueId;

	@NotNull
	private final Long editorId;

	private final String title;
	private final String description;
	private final IssueStatus status;
	private final IssuePriority priority;

	@Builder
	public EditIssueCommand(Long issueId, Long editorId, String title, String description, IssueStatus status, IssuePriority priority) {
		this.issueId = issueId;
		this.editorId = editorId;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;

		this.validateAndIfViolatedThrows();
	}
}
