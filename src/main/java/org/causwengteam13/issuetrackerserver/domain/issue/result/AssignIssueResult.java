package org.causwengteam13.issuetrackerserver.domain.issue.result;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

@Builder
public record AssignIssueResult(
	Long issueId,
	Long assignerId,
	Long assigneeId,
	IssueStatus status
) {
}
