package org.causwengteam13.issuetrackerserver.domain.user.usecase;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.user.command.FindUsersCommand;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.causwengteam13.issuetrackerserver.domain.user.result.FindUsersResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindUsers {

	private final UserRepository userRepository;

	public FindUsersResult execute(FindUsersCommand command) {
		List<User> users = userRepository.findUsersByCommand(command);

		return new FindUsersResult(users.stream().map(user -> new FindUsersResult.UserResult(
			user.getId(),
			user.getName()
		)).toList());
	}
}
