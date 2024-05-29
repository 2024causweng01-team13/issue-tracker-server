package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request;

import lombok.Getter;

@Getter
public class AssignIssueRequest {
	private Long assignerId;
	private Long assigneeId;
	private String comment;
}
