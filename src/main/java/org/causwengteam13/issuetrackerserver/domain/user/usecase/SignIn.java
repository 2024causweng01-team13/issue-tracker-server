package org.causwengteam13.issuetrackerserver.domain.user.usecase;

import org.causwengteam13.issuetrackerserver.domain.user.command.SignInCommand;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.PasswordIncorrectProblem;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.causwengteam13.issuetrackerserver.domain.user.result.SignInResult;
import org.causwengteam13.issuetrackerserver.domain.user.service.PasswordService;
import org.causwengteam13.issuetrackerserver.domain.user.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignIn {

	private final UserRepository userRepository;
	private final TokenService tokenService;
	private final PasswordService passwordService;

	public SignInResult execute(SignInCommand command) {
		User user = userRepository.findByLoginId(command.getLoginId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getLoginId()));

		if (!passwordService.isCorrect(user, command.getPassword())) {
			throw new PasswordIncorrectProblem();
		}

		String token = tokenService.createIdToken(user);

		return new SignInResult(token);
	}
}
