package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

@Builder
public record CreateIssueResponse(
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
