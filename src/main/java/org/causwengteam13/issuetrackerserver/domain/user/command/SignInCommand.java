package org.causwengteam13.issuetrackerserver.domain.user.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInCommand extends SelfValidator<SignInCommand> {

	@NotBlank
	private final String loginId;

	@NotBlank
	private final String password;

	@Builder
	public SignInCommand(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;

		validateAndIfViolatedThrows();
	}
}
