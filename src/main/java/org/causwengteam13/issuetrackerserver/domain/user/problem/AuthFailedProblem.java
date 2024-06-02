package org.causwengteam13.issuetrackerserver.domain.user.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class AuthFailedProblem extends Problem {

	public AuthFailedProblem(Exception cause) {
		super(ProblemCategory.UNPROCESSABLE, "user/auth-failed", "Authentication failed", cause);
	}

	public AuthFailedProblem(String message) {
		super(ProblemCategory.UNPROCESSABLE, "user/auth-failed", message, null);
	}
}
