package org.causwengteam13.issuetrackerserver.domain.project.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddMemberToProjectCommand extends SelfValidator<AddMemberToProjectCommand> {

	private final Long projectId;
	private final Long adderId;
	private final Long memberId;

	@Builder
	public AddMemberToProjectCommand(Long projectId, Long adderId, Long memberId) {
		this.projectId = projectId;
		this.adderId = adderId;
		this.memberId = memberId;
	}
}
