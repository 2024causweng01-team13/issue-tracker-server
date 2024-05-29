package org.causwengteam13.issuetrackerserver.domain.comment.result;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record CreateCommentResult(
	Long id,
	String authorName,
	String content,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
