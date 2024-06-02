package org.causwengteam13.issuetrackerserver.domain.project.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class ProjectNotFoundProblem extends Problem {
	public ProjectNotFoundProblem(Long projectId) {
		super(ProblemCategory.NOT_FOUND, "project/not-found", "Project not found. ProjectId: " + projectId, null);
	}
}
