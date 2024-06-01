package org.causwengteam13.issuetrackerserver.presentation.restapi.user.response;

import java.util.List;

import lombok.Builder;

public record FindUsersResponse(
	List<UserResponse> users
) {

	@Builder
	public record UserResponse(
		Long id,
		String name
	) {
	}
}
