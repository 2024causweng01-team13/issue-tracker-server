package org.causwengteam13.issuetrackerserver.domain.issue.problem;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.causwengteam13.issuetrackerserver.common.ProblemCategory;

public class IssueNotFoundProblem extends Problem {
	public IssueNotFoundProblem(Long id) {
		super(ProblemCategory.NOT_FOUND, "issue/not-found", "Issue not found. ID: " + id, null);
	}
}
