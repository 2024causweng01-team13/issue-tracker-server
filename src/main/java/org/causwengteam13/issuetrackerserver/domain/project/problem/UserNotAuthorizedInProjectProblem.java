package org.causwengteam13.issuetrackerserver.domain.project.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class UserNotAuthorizedInProjectProblem extends Problem {
	public UserNotAuthorizedInProjectProblem() {
		super(ProblemCategory.UNAUTHORIZED, "project/user-not-authorized", "User is not authorized in the project", null);
	}
}
