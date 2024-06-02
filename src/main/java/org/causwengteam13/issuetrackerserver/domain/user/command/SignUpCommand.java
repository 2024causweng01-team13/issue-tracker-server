package org.causwengteam13.issuetrackerserver.domain.user.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpCommand extends SelfValidator<SignUpCommand> {

	@NotBlank
	private final String loginId;

	@NotBlank
	private final String name;

	@NotBlank
	private final String password;

	@Builder
	public SignUpCommand(String loginId, String name, String password) {
		this.loginId = loginId;
		this.name = name;
		this.password = password;

		validateAndIfViolatedThrows();
	}
}
