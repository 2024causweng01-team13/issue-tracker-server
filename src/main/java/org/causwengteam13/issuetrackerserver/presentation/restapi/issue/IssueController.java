package org.causwengteam13.issuetrackerserver.presentation.restapi.issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.issue.command.AssignIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.command.CreateIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.command.FindIssueByIdCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.command.FindIssuesCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssuePriority;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;
import org.causwengteam13.issuetrackerserver.domain.issue.result.AssignIssueResult;
import org.causwengteam13.issuetrackerserver.domain.issue.result.CreateIssueResult;
import org.causwengteam13.issuetrackerserver.domain.issue.result.FindIssueByIdResult;
import org.causwengteam13.issuetrackerserver.domain.issue.result.FindIssuesResult;
import org.causwengteam13.issuetrackerserver.domain.issue.usecase.AssignIssue;
import org.causwengteam13.issuetrackerserver.domain.issue.usecase.CreateIssue;
import org.causwengteam13.issuetrackerserver.domain.issue.usecase.FindIssueById;
import org.causwengteam13.issuetrackerserver.domain.issue.usecase.FindIssues;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request.AssignIssueRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request.CreateIssueRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.request.FindIssuesRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response.AssignIssueResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response.CreateIssueResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response.FindIssueByIdResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.issue.response.FindIssuesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/issues")
public class IssueController {

	private final AssignIssue assignIssue;
	private final FindIssues findIssues;
	private final CreateIssue createIssue;
	private final FindIssueById findIssueById;

	@Operation(summary = "이슈 검색 및 목록 조회")
	@PostMapping("/find")
	public CommonResponse<FindIssuesResponse> findIssues(@RequestBody FindIssuesRequest request) {
		FindIssuesCommand command = FindIssuesCommand.builder()
				.projectId(request.getProjectId())
				.assigneeName(request.getAssigneeName())
				.reporterName(request.getReporterName())
				.status(request.getStatus())
				.searchAs(request.getSearchAs()).build();

		FindIssuesResult result = findIssues.execute(command);

		FindIssuesResponse response = new FindIssuesResponse(
				result.getIssues().stream().map(issuesResult -> FindIssuesResponse.IssueResponse.builder()
						.id(issuesResult.getId())
						.title(issuesResult.getTitle())
						.description(issuesResult.getDescription())
						.assigneeName(issuesResult.getAssigneeName())
						.reporterName(issuesResult.getReporterName())
						.fixerName(issuesResult.getFixerName())
						.priority(issuesResult.getPriority())
						.status(issuesResult.getStatus())
						.createdAt(issuesResult.getCreatedAt())
						.updatedAt(issuesResult.getUpdatedAt())
						.build()).toList()
		);

		return CommonResponse.success("Find Issues success", response);
	}

	@Operation(summary = "이슈 상세 조회")
	@GetMapping("/{issueId}")
	public CommonResponse<FindIssueByIdResponse> findIssueById(@PathVariable(value = "issueId") Long issueId) {

		FindIssueByIdCommand command = FindIssueByIdCommand.builder()
				.issueId(issueId)
				.build();

		FindIssueByIdResult result = findIssueById.execute(command);

		List<FindIssueByIdResponse.CommentResponse> commentResponses = new ArrayList<>();
		for(Comment c : result.getComments()) {
			FindIssueByIdResponse.CommentResponse Comm = FindIssueByIdResponse.CommentResponse.builder()
					.id(c.getId())
					.content(c.getContent())
					.authorName(c.getAuthor().getName())
					.createdAt(c.getCreatedAt())
					.updatedAt(c.getUpdatedAt())
					.build();

			commentResponses.add(Comm);
		}

		FindIssueByIdResponse response = FindIssueByIdResponse.builder()
				.id(result.getId())
				.title(result.getTitle())
				.description(result.getDescription())
				.reporterName(result.getReporterName())
				.assigneeName(result.getAssigneeName())
				.fixerName(result.getFixerName())
				.comments(commentResponses)
				.priority(result.getPriority())
				.status(result.getStatus())
				.createdAt(result.getCreatedAt())
				.updatedAt(result.getUpdatedAt())
				.build();

		return CommonResponse.success("Find issue success", response);
  }

	@Operation(summary = "이슈 생성")
	@PostMapping
	public CommonResponse<CreateIssueResponse> createIssue(@RequestBody CreateIssueRequest request) {

		CreateIssueCommand command = CreateIssueCommand.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.projectId(request.getProjectId())
				.reporterId(request.getReporterId())
				.build();


		CreateIssueResult result = createIssue.execute(command);

		CreateIssueResponse response = CreateIssueResponse.builder()
				.id(result.getId())
				.title(result.getTitle())
				.description(result.getDescription())
				.reporterName(result.getReporterName())
				.assigneeName(result.getAssigneeName())
				.fixerName(result.getFixerName())
				.priority(result.getPriority())
				.status(result.getStatus())
				.createdAt(result.getCreatedAt())
				.updatedAt(result.getUpdatedAt())
				.build();

		return CommonResponse.success("Create Issue success", response);
	}

	@Operation(summary = "이슈 할당")
	@PostMapping("/{issueId}/assign")
	public CommonResponse<AssignIssueResponse> assignIssue(
		@PathVariable(value = "issueId") Long issueId,
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
			.updatedAt(result.updatedAt())
			.build();

		return CommonResponse.success("Assign Issue success", response);
	}
}
