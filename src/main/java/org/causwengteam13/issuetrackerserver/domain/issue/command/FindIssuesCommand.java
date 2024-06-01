package org.causwengteam13.issuetrackerserver.domain.issue.command;

import lombok.Builder;

@Builder
public record FindIssuesCommand(Long projectId, String assigneeName, String reporterName,
                                String status, String searchAs) {

}
