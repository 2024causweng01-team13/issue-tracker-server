package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

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
		private final Long projectId;
		private final String title;
		private final String description;
		private final UserResponse manager;
		private final ProjectStatus status;

		public static ProjectResponse from(FindProjectsResult.ProjectResult result) {
			return ProjectResponse.builder()
				.projectId(result.getProjectId())
				.title(result.getTitle())
				.description(result.getDescription())
				.manager(result.getManager() == null ? null : UserResponse.builder()
					.userId(result.getManager().userId())
					.name(result.getManager().name())
					.build())
				.build();
		}

		@Builder
		public record UserResponse(
			Long userId,
			String name
		) {
		}
	}
}
