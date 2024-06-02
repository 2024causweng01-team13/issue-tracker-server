package org.causwengteam13.issuetrackerserver.domain.project.result;

import lombok.Builder;

@Builder
public record AddMemberToProjectResult(
	Long projectId,
	Long memberId
) {
}
