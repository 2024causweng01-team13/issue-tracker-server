package org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Getter;

@Getter
public class EditIssueRequest {

	private Long editorId;
	private String title;
	private String description;
	private IssueStatus status;
	private IssuePriority priority;
}
