package org.causwengteam13.issuetrackerserver.domain.user.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class PasswordIncorrectProblem extends Problem {

	public PasswordIncorrectProblem() {
		super(ProblemCategory.UNPROCESSABLE, "user/password-incorrect", "Password is incorrect", null);
	}
}
