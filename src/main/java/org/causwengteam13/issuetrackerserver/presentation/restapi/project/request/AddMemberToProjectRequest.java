package org.causwengteam13.issuetrackerserver.presentation.restapi.project.request;

import lombok.Getter;

@Getter
public class AddMemberToProjectRequest {

	private Long adderId;
	private Long memberId;
}
