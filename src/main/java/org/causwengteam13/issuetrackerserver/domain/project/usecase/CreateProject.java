package org.causwengteam13.issuetrackerserver.domain.project.usecase;

import org.causwengteam13.issuetrackerserver.domain.project.command.CreateProjectCommand;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.repository.ProjectRepository;
import org.causwengteam13.issuetrackerserver.domain.project.result.CreateProjectResult;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProject {

	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;

	public CreateProjectResult execute(CreateProjectCommand command) {
		User manager = userRepository.findById(command.getManagerId())
				.orElseThrow(() -> new UserNotFoundProblem(command.getManagerId()));

		Project project = Project.of(manager, command.getTitle(), command.getDescription());
		Project savedProject = projectRepository.save(project);

		return new CreateProjectResult(savedProject.getId());
	}
}
