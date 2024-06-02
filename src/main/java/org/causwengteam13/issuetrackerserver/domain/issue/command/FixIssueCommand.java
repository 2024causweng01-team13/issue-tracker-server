package org.causwengteam13.issuetrackerserver.domain.issue.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FixIssueCommand extends SelfValidator<FixIssueCommand> {

	@NotNull
	private final Long issueId;

	@NotNull
	private final Long fixerId;

	private final String comment;

	@Builder
	public FixIssueCommand(Long issueId, Long fixerId, String comment) {
		this.issueId = issueId;
		this.fixerId = fixerId;
		this.comment = comment;

		this.validateAndIfViolatedThrows();
	}
}
