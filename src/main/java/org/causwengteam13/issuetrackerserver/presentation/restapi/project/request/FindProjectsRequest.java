package org.causwengteam13.issuetrackerserver.presentation.restapi.project.request;

import lombok.Getter;

@Getter
public class FindProjectsRequest {
	private String title;
	private String description;
}
