package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response;

import java.time.LocalDateTime;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

public record FindIssuesResponse(List<IssueResponse> issues) {

	@Builder
	public record IssueResponse(
		Long id,
		String title,
		String description,
		String reporterName,
		String assigneeName,
		String fixerName,
		IssuePriority priority,
		IssueStatus status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
	) {
	}
}
