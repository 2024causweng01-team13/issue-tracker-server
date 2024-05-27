package org.causwengteam13.issuetrackerserver.domain.project.repository;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.project.command.FindProjectsCommand;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;

public interface ProjectRepositoryCustom {

	List<Project> findProjects(FindProjectsCommand command);
}
