package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

@Builder
public record FixIssueResponse(
	Long issueId,
	String fixerName,
	IssueStatus status,
	LocalDateTime updatedAt
) {
}
