package org.causwengteam13.issuetrackerserver.domain.user.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class UserNotFoundProblem extends Problem {
	public UserNotFoundProblem(Long id) {
		super(ProblemCategory.NOT_FOUND, "user/not-found", "User not found. ID: " + id, null);
	}

	public UserNotFoundProblem(String content) {
		super(ProblemCategory.NOT_FOUND, "user/not-found", "User not found. " + content, null);
	}

	public static UserNotFoundProblem ofLoginId(String loginId) {
		return new UserNotFoundProblem("loginId: " + loginId);
	}
}
