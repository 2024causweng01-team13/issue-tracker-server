package org.causwengteam13.issuetrackerserver.presentation.restapi.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {

	@NotBlank
	private String loginId;
	@NotBlank
	private String password;
}
