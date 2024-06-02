package org.causwengteam13.issuetrackerserver.domain.user.usecase;

import org.causwengteam13.issuetrackerserver.domain.user.command.SignUpCommand;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserAlreadyExistsProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.causwengteam13.issuetrackerserver.domain.user.result.SignUpResult;
import org.causwengteam13.issuetrackerserver.domain.user.service.PasswordService;
import org.causwengteam13.issuetrackerserver.domain.user.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUp {

	private final UserRepository userRepository;
	private final TokenService tokenService;
	private final PasswordService passwordService;

	public SignUpResult execute(SignUpCommand command) {
		if (userRepository.existsByLoginId(command.getLoginId())) {
			throw new UserAlreadyExistsProblem(command.getLoginId());
		}

		if (userRepository.existsByName(command.getName())) {
			throw new UserAlreadyExistsProblem(command.getName());
		}

		String encodedPassword = passwordService.encode(command.getPassword());

		User user = User.builder()
				.loginId(command.getLoginId())
				.name(command.getName())
				.password(encodedPassword)
				.build();

		User savedUser = userRepository.save(user);

		String token = tokenService.createIdToken(savedUser.getId());

		return new SignUpResult(token);
	}
}
