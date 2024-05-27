package org.causwengteam13.issuetrackerserver.domain.project.command;

import lombok.Builder;

@Builder
public record FindProjectsCommand(String title, String description) {

}
