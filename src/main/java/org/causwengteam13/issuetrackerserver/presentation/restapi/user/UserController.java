package org.causwengteam13.issuetrackerserver.presentation.restapi.user;

import org.causwengteam13.issuetrackerserver.domain.user.command.FindUsersCommand;
import org.causwengteam13.issuetrackerserver.domain.user.result.FindUsersResult;
import org.causwengteam13.issuetrackerserver.domain.user.usecase.FindUsers;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.user.request.FindUsersRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.user.response.FindUsersResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final FindUsers findUsers;

	@PostMapping("/find")
	public CommonResponse<FindUsersResponse> findUsers(@RequestBody FindUsersRequest request) {
		FindUsersCommand command = new FindUsersCommand(request.getName());

		FindUsersResult result = findUsers.execute(command);

		FindUsersResponse response = new FindUsersResponse(
			result.users().stream().map(user -> new FindUsersResponse.UserResponse(
				user.id(),
				user.name()
			)).toList()
		);

		return CommonResponse.success("Users found", response);
	}
}
