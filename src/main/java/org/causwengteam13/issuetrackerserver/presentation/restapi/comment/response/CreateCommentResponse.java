package org.causwengteam13.issuetrackerserver.presentation.restapi.comment.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentResponse {
	private final Long commentId;
}
