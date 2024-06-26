package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

@Builder
public record AssignIssueResponse(
	Long issueId,
	String assignerName,
	String assigneeName,
	IssueStatus status,
	LocalDateTime updatedAt
) {
}
