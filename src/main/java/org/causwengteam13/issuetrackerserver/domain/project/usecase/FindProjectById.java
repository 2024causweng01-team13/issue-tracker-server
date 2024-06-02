package org.causwengteam13.issuetrackerserver.domain.project.usecase;

import org.causwengteam13.issuetrackerserver.domain.project.command.FindProjectByIdCommand;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.ProjectNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.project.repository.ProjectRepository;
import org.causwengteam13.issuetrackerserver.domain.project.result.FindProjectByIdResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindProjectById {

	private final ProjectRepository projectRepository;

	public FindProjectByIdResult execute(FindProjectByIdCommand command) {
		Project project = projectRepository.findById(command.getProjectId())
			.orElseThrow(() -> new ProjectNotFoundProblem(command.getProjectId()));

		return FindProjectByIdResult.builder()
			.id(project.getId())
			.title(project.getTitle())
			.description(project.getDescription())
			.managerName(project.getManager() != null ? project.getManager().getName() : null)
			.status(project.getStatus())
			.createdAt(project.getCreatedAt())
			.updatedAt(project.getUpdatedAt())
			.build();
	}
}
