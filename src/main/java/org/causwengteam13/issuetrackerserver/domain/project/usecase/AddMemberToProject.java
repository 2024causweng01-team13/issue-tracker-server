package org.causwengteam13.issuetrackerserver.domain.project.usecase;

import org.causwengteam13.issuetrackerserver.domain.project.command.AddMemberToProjectCommand;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.ProjectNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.project.repository.ProjectRepository;
import org.causwengteam13.issuetrackerserver.domain.project.result.AddMemberToProjectResult;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AddMemberToProject {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;

	public AddMemberToProjectResult execute(AddMemberToProjectCommand command) {
		Project project = projectRepository.findById(command.getProjectId())
			.orElseThrow(() -> new ProjectNotFoundProblem(command.getProjectId()));
		User adder = userRepository.findById(command.getAdderId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getAdderId()));
		User member = userRepository.findById(command.getMemberId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getMemberId()));

		project.addMember(adder, member);

		return AddMemberToProjectResult.builder()
			.projectId(project.getId())
			.memberId(member.getId())
			.build();
	}
}
