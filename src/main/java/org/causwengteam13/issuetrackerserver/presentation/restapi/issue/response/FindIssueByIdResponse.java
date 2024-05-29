package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response;

import java.time.LocalDateTime;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindIssueByIdResponse {
	private final Long id;
	private final String title;
	private final String description;
	private final String reporterName;
	private final String assigneeName;
	private final String fixerName;
	private final List<CommentResponse> comments;
	private final IssuePriority priority;
	private final IssueStatus status;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	@Builder
	public FindIssueByIdResponse(Long id, String title, String description, String reporterName, String assigneeName,
		String fixerName, List<CommentResponse> comments, IssuePriority priority, IssueStatus status,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.reporterName = reporterName;
		this.assigneeName = assigneeName;
		this.fixerName = fixerName;
		this.comments = comments;
		this.priority = priority;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Builder
	public record CommentResponse(
		Long id,
		String content,
		String authorName,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
	) {
	}
}
