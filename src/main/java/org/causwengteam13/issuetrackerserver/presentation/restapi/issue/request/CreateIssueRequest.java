package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request;

import lombok.Getter;

@Getter
public class CreateIssueRequest {
	private String title;
	private String description;
	private Long projectId;
	private Long reporterId;
}
