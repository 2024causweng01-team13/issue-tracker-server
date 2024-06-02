package org.causwengteam13.issuetrackerserver.domain.project.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.FindProjectById;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindProjectByIdCommand extends SelfValidator<FindProjectById> {

	@NotNull
	private final Long projectId;

	public FindProjectByIdCommand(Long projectId) {
		this.projectId = projectId;
	}
}
