package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import lombok.Builder;

@Builder
public record AddMemberToProjectResponse(
	Long projectId,
	Long memberId
) {
}
