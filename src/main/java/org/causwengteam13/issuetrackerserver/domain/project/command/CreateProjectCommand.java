package org.causwengteam13.issuetrackerserver.domain.project.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProjectCommand extends SelfValidator<CreateProjectCommand> {

	@NotBlank
	private final String title;

	private final String description;

	@NotNull
	private final Long managerId;

	@Builder
	public CreateProjectCommand(String title, String description, Long managerId) {
		this.title = title;
		this.description = description;
		this.managerId = managerId;

		validateAndIfViolatedThrows();
	}
}
