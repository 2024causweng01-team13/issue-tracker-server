package org.causwengteam13.issuetrackerserver.presentation.restapi.issue;

import org.causwengteam13.issuetrackerserver.domain.issue.command.AssignIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.result.AssignIssueResult;
import org.causwengteam13.issuetrackerserver.domain.issue.usecase.AssignIssue;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request.AssignIssueRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response.AssignIssueResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/issues")
public class IssueController {

	private final AssignIssue assignIssue;

	@PostMapping("/:issueId/assign")
	public CommonResponse<AssignIssueResponse> assignIssue(
		@RequestParam(value = "issueId") Long issueId,
		@RequestBody AssignIssueRequest request
	) {
		AssignIssueCommand command = AssignIssueCommand.builder()
			.issueId(issueId)
			.assignerId(request.getAssignerId())
			.assigneeId(request.getAssigneeId())
			.comment(request.getComment())
			.build();

		AssignIssueResult result = assignIssue.execute(command);

		AssignIssueResponse response = AssignIssueResponse.builder()
			.issueId(result.issueId())
			.assignerName(result.assignerName())
			.assigneeName(result.assigneeName())
			.status(result.status())
			.build();

		return CommonResponse.success("Assign Issue success", response);
	}
}
