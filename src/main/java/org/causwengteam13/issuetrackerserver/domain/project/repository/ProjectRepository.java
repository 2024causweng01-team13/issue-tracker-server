package org.causwengteam13.issuetrackerserver.domain.project.repository;

import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {
}
