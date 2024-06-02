package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request;

import lombok.Getter;

@Getter
public class FindIssuesRequest {
	private Long projectId;
	private String assigneeName;
	private String reporterName;
	private String status;
	private String searchAs;
	//PROJECT_ID, ASSIGNEE_NAME, REPORTER_NAME, STATUS
}
