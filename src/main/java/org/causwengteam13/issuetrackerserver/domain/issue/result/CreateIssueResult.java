package org.causwengteam13.issuetrackerserver.domain.issue.result;

import lombok.Builder;
import lombok.Getter;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import java.time.LocalDateTime;

@Getter
public class CreateIssueResult {
    private final Long id;
    private final String title;
    private final String description;
    private final String reporterName;
    private final String assigneeName;
    private final String fixerName;
    private final IssuePriority priority;
    private final IssueStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public CreateIssueResult(Issue issue) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.description = issue.getDescription();
        if(issue.getReporter() == null) {
            this.reporterName = null;
        } else {
            this.reporterName = issue.getReporter().getName();
        }
        if(issue.getAssignee() == null) {
            this.assigneeName = null;
        } else {
            this.assigneeName = issue.getAssignee().getName();
        }
        if(issue.getFixer() == null) {
            this.fixerName = null;
        } else {
            this.fixerName = issue.getFixer().getName();
        }
        this.priority = issue.getPriority();
        this.status = issue.getStatus();
        this.createdAt = issue.getCreatedAt();
        this.updatedAt = issue.getUpdatedAt();
    }

}
