package org.causwengteam13.issuetrackerserver.presentation.restapi.comment.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record CreateCommentResponse(
	Long id,
	String authorName,
	String content,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
