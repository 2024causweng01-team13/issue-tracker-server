package org.causwengteam13.issuetrackerserver.presentation.restapi.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequest {

	@NotBlank
	private String loginId;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
}
