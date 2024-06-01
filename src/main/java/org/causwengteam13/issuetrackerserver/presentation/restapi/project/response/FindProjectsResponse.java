package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import java.time.LocalDateTime;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;

import lombok.Builder;
import lombok.Getter;

public record FindProjectsResponse(List<ProjectResponse> projects) {

	@Getter
	@Builder
	public static class ProjectResponse {
		private final Long id;
		private final String title;
		private final String description;
		private final String managerName;
		private final ProjectStatus status;
		private final LocalDateTime createdAt;
	}
}
