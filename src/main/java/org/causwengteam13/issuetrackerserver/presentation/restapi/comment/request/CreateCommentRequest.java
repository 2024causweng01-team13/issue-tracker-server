package org.causwengteam13.issuetrackerserver.presentation.restapi.comment.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

	private Long authorId;
	private Long parentIssueId;
	private String content;
}
