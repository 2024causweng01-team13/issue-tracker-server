package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record FindProjectByIdResult(
	Long id,
	String title,
	String description,
	String managerName,
	ProjectStatus status,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
