package org.causwengteam13.issuetrackerserver.domain.issue.result;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

@Builder
public record AssignIssueResult(
	Long issueId,
	String assignerName,
	String assigneeName,
	IssueStatus status,
	LocalDateTime updatedAt
) {
}
