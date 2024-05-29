package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.time.LocalDateTime;
import java.util.List;

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
			.id(project.getId())
			.title(project.getTitle())
			.description(project.getDescription())
			.manager(project.getManager())
			.status(project.getStatus())
			.createdAt(project.getCreatedAt())
			.updatedAt(project.getUpdatedAt())
			.build()).toList();
	}

	@Getter
	public static class ProjectResult {
		private final Long id;
		private final String title;
		private final String description;
		private final String managerName;
		private final ProjectStatus status;
		private final LocalDateTime createdAt;
		private final LocalDateTime updatedAt;

		@Builder
		private ProjectResult(Long id, String title, String description, User manager, ProjectStatus status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
			this.id = id;
			this.title = title;
			this.description = description;
			this.managerName = manager == null ? null : manager.getName();
			this.status = status;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}
	}
}
