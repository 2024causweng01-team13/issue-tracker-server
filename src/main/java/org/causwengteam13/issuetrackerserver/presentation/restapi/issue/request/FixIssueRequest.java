package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request;

import lombok.Getter;

@Getter
public class FixIssueRequest {

	private Long fixerId;
	private String comment;
}
