package org.causwengteam13.issuetrackerserver.presentation.restapi.project.request;

import lombok.Getter;

@Getter
public class CreateProjectRequest {
	private String title;
	private String description;
	private Long managerId;
}
