package org.causwengteam13.issuetrackerserver.domain.user.result;

import java.util.List;

public record FindUsersResult(
	List<UserResult> users
) {

	public record UserResult(
		Long id,
		String name
	) {
	}
}
