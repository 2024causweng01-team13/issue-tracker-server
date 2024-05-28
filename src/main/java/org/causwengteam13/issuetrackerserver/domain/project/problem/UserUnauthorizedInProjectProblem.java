package org.causwengteam13.issuetrackerserver.domain.project.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class UserUnauthorizedInProjectProblem extends Problem {
	public UserUnauthorizedInProjectProblem(Long userId) {
		super(ProblemCategory.UNAUTHORIZED, "project/user-unauthorized", "User is not authorized in the project. userId: " + userId, null);
	}
}
