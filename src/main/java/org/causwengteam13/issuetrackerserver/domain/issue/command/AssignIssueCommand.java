package org.causwengteam13.issuetrackerserver.domain.issue.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AssignIssueCommand extends SelfValidator<AssignIssueCommand> {

	@NotNull
	private final Long issueId;

	@NotNull
	private final Long assignerId;

	@NotNull
	private final Long assigneeId;

	@NotBlank
	private final String comment;

	@Builder
	public AssignIssueCommand(Long issueId, Long assignerId, Long assigneeId, String comment) {
		this.issueId = issueId;
		this.assignerId = assignerId;
		this.assigneeId = assigneeId;
		this.comment = comment;

		this.validateAndIfViolatedThrows();
	}
}
