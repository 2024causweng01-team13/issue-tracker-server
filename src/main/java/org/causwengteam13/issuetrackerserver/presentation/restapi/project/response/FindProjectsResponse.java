package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import java.time.LocalDateTime;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;
import org.causwengteam13.issuetrackerserver.domain.project.result.FindProjectsResult;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindProjectsResponse {
	private final List<ProjectResponse> projects;

	public static FindProjectsResponse from(FindProjectsResult result) {
		return FindProjectsResponse.builder()
			.projects(result.getProjects().stream().map(ProjectResponse::from).toList())
			.build();
	}

	@Getter
	@Builder
	public static class ProjectResponse {
		private final Long id;
		private final String title;
		private final String description;
		private final String managerName;
		private final ProjectStatus status;
		private final LocalDateTime createdAt;

		public static ProjectResponse from(FindProjectsResult.ProjectResult result) {
			return ProjectResponse.builder()
				.id(result.getId())
				.title(result.getTitle())
				.description(result.getDescription())
				.managerName(result.getManagerName())
				.build();
		}
	}
}
