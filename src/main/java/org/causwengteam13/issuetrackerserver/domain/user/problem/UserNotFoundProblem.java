package org.causwengteam13.issuetrackerserver.domain.user.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class UserNotFoundProblem extends Problem {
	public UserNotFoundProblem(Long id) {
		super(ProblemCategory.NOT_FOUND, "user/not-found", "User not found. ID: " + id, null);
	}
}
