package org.causwengteam13.issuetrackerserver.domain.issue.result;

import lombok.Builder;
import lombok.Getter;
import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response.FindIssueByIdResponse;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FindIssueByIdResult {
    private final Long id;
    private final String title;
    private final String description;
    private final String reporterName;
    private final String assigneeName;
    private final String fixerName;
    private final List<Comment> comments;
    private final IssuePriority priority;
    private final IssueStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    @Builder
    public FindIssueByIdResult(Issue issue) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.description = issue.getDescription();
        this.reporterName = issue.getReporter().getName();
        this.assigneeName = issue.getAssignee().getName();
        this.fixerName = issue.getFixer().getName();
        this.comments = issue.getComments();
        this.priority = issue.getPriority();
        this.status = issue.getStatus();
        this.createdAt = issue.getCreatedAt();
        this.updatedAt = issue.getUpdatedAt();
    }
}
