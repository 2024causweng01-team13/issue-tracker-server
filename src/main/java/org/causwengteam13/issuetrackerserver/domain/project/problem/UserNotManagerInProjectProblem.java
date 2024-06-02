package org.causwengteam13.issuetrackerserver.domain.project.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class UserNotManagerInProjectProblem extends Problem {

	public UserNotManagerInProjectProblem(Long userId) {
		super(ProblemCategory.UNAUTHORIZED, "user/not-manager-in-project", "User is not a manager in the project. Id: " + userId, null);
	}
}
