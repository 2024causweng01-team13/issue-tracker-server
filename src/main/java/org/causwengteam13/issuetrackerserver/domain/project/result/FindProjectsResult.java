package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.util.List;
import java.util.Objects;

import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindProjectsResult {
	private final List<ProjectResult> projects;

	public FindProjectsResult(List<Project> projects) {
		this.projects = projects.stream().map(project -> ProjectResult.builder()
			.projectId(project.getId())
			.title(project.getTitle())
			.description(project.getDescription())
			.manager(project.getManager())
			.status(project.getStatus())
			.build()).toList();
	}

	@Getter
	public static class ProjectResult {
		private final Long projectId;
		private final String title;
		private final String description;
		private final UserResult manager;
		private final ProjectStatus status;

		@Builder
		private ProjectResult(Long projectId, String title, String description, User manager, ProjectStatus status) {
			this.projectId = projectId;
			this.title = title;
			this.description = description;
			this.manager = manager == null ? null : UserResult.builder()
				.userId(manager.getId())
				.name(manager.getName())
				.build();
			this.status = status;
		}

		@Builder
		public record UserResult(
			Long userId,
			String name
		) {
		}
	}
}
