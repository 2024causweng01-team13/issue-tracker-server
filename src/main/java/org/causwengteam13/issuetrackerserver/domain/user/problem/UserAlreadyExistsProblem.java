package org.causwengteam13.issuetrackerserver.domain.user.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class UserAlreadyExistsProblem extends Problem {

	public UserAlreadyExistsProblem(String info) {
		super(ProblemCategory.INVALID_REQUEST, "user/already-exists", "User already exists. " + info, null);
	}
}
