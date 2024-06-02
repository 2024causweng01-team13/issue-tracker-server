package org.causwengteam13.issuetrackerserver.domain.issue.result;

import lombok.Builder;
import lombok.Getter;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FindIssuesResult {
    private final List<IssuesResult> issues;

    public FindIssuesResult(List<Issue> issues) {
        this.issues = issues.stream().map(issue -> IssuesResult.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .reporterName(issue.getReporter() != null ? issue.getReporter().getName() : null)
                .assigneeName(issue.getAssignee() != null ? issue.getAssignee().getName() : null)
                .fixerName(issue.getFixer() != null ? issue.getFixer().getName() : null)
                .priority(issue.getPriority())
                .status(issue.getStatus())
                .createdAt(issue.getCreatedAt())
                .updatedAt(issue.getUpdatedAt())
                .build()).toList();
    }

    @Getter
    public static class IssuesResult {
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
        private IssuesResult(Long id, String title, String description, String reporterName,
                             String assigneeName, String fixerName, IssuePriority priority,
                             IssueStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.reporterName = reporterName;
            this.assigneeName = assigneeName;
            this.fixerName = fixerName;
            this.priority = priority;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
}
