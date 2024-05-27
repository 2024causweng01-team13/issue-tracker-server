package org.causwengteam13.issuetrackerserver.domain.project.usecase;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.project.command.FindProjectsCommand;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.repository.ProjectRepository;
import org.causwengteam13.issuetrackerserver.domain.project.result.FindProjectsResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindProjects {

	private final ProjectRepository projectRepository;

	public FindProjectsResult execute(FindProjectsCommand command) {
		List<Project> projects = projectRepository.findProjects(command);

		return new FindProjectsResult(projects);
	}
}
