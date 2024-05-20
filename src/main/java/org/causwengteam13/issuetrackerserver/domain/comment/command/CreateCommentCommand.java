package org.causwengteam13.issuetrackerserver.domain.comment.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentCommand extends SelfValidator<CreateCommentCommand> {

	@NotNull
	private final Long authorId;
	@NotNull
	private final Long parentIssueId;
	@NotBlank
	private final String content;

	@Builder
	private CreateCommentCommand(Long authorId, Long parentIssueId, String content) {
		this.authorId = authorId;
		this.parentIssueId = parentIssueId;
		this.content = content;

		validateAndIfViolatedThrows();
	}
}
