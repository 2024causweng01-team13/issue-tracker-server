package org.causwengteam13.issuetrackerserver.domain.issue.command;

import lombok.Builder;
import lombok.Getter;
import org.causwengteam13.issuetrackerserver.common.SelfValidator;

@Getter
public class FindIssueByIdCommand {
    private final Long issueId;

    @Builder
    public FindIssueByIdCommand(Long issueId) {
        this.issueId = issueId;
    }
}
