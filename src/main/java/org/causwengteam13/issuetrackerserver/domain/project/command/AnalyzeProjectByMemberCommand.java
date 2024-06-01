package org.causwengteam13.issuetrackerserver.domain.project.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import lombok.Getter;

@Getter
public class AnalyzeProjectByMemberCommand extends SelfValidator<AnalyzeProjectByMemberCommand> {

	private final Long projectId;

	public AnalyzeProjectByMemberCommand(Long projectId) {
		this.projectId = projectId;

		validateAndIfViolatedThrows();
	}
}
