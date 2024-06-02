package org.causwengteam13.issuetrackerserver.domain.issue.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.causwengteam13.issuetrackerserver.common.SelfValidator;

@Getter

public class CreateIssueCommand extends SelfValidator<CreateIssueCommand> {
    @NotBlank
    private final String title;
    private final String description;
    private final Long projectId;
    @NotNull
    private final Long reporterId;

    @Builder
    public CreateIssueCommand(String title, String description, Long projectId, Long reporterId) {
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.reporterId = reporterId;

        validateAndIfViolatedThrows();
    }
}
