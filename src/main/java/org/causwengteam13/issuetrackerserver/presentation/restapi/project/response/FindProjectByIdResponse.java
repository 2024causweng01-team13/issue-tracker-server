package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record FindProjectByIdResponse(
	Long id,
	String title,
	String description,
	String managerName,
	ProjectStatus status,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
