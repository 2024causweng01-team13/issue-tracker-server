package org.causwengteam13.issuetrackerserver.presentation.restapi.project;

import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByDateCommand;
import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByMemberCommand;
import org.causwengteam13.issuetrackerserver.domain.project.command.CreateProjectCommand;
import org.causwengteam13.issuetrackerserver.domain.project.command.FindProjectsCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByDateResult;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByMemberResult;
import org.causwengteam13.issuetrackerserver.domain.project.result.CreateProjectResult;
import org.causwengteam13.issuetrackerserver.domain.project.result.FindProjectsResult;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.AnalyzeProjectByDate;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.AnalyzeProjectByMember;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.CreateProject;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.FindProjects;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.AnalyzeProjectByDateRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.AnalyzeProjectByMemberRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.CreateProjectRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.FindProjectsRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.AnalyzeProjectByDateResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.AnalyzeProjectByMemberResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.CreateProjectResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.FindProjectsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

	private final CreateProject createProject;
	private final FindProjects findProjects;
	private final AnalyzeProjectByDate analyzeProjectByDate;
	private final AnalyzeProjectByMember analyzeProjectByMember;

	@Operation(summary = "프로젝트 생성")
	@PostMapping
	public CommonResponse<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest request) {
		CreateProjectCommand command = CreateProjectCommand.builder()
				.managerId(request.getManagerId())
				.title(request.getTitle())
				.description(request.getDescription())
				.build();

		CreateProjectResult result = createProject.execute(command);

		CreateProjectResponse response = new CreateProjectResponse(result.projectId());

		return CommonResponse.success("Project created", response);
	}

	@Operation(summary = "프로젝트 검색 및 목록 조회")
	@PostMapping("/find")
	public CommonResponse<FindProjectsResponse> findProjects(@RequestBody FindProjectsRequest request) {
		FindProjectsCommand command = FindProjectsCommand.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.build();

		FindProjectsResult result = findProjects.execute(command);

		FindProjectsResponse response = new FindProjectsResponse(
			result.getProjects().stream().map(p -> FindProjectsResponse.ProjectResponse.builder()
				.id(p.getId())
				.title(p.getTitle())
				.description(p.getDescription())
				.managerName(p.getManagerName())
				.status(p.getStatus())
				.createdAt(p.getCreatedAt())
				.build()
			).toList()
		);

		return CommonResponse.success("Projects found", response);
	}

	@Operation(summary = "날짜별 프로젝트 분석")
	@PostMapping("/analytics/date")
	public CommonResponse<AnalyzeProjectByDateResponse> analyzeProjectsByDate(@RequestBody AnalyzeProjectByDateRequest request) {
		AnalyzeProjectByDateCommand command = AnalyzeProjectByDateCommand.builder()
				.projectId(request.getProjectId())
				.unit(request.getUnit())
				.startDate(request.getStartDate())
				.endDate(request.getEndDate())
				.build();

		AnalyzeProjectByDateResult result = analyzeProjectByDate.execute(command);

		AnalyzeProjectByDateResponse response = new AnalyzeProjectByDateResponse(
			result.dateStatistics().stream().map(p -> AnalyzeProjectByDateResponse.DateStatistics.builder()
				.date(p.date())
				.count(p.count())
				.build()
			).toList()
		);

		return CommonResponse.success("Project is analyzed by date", response);
	}

	@Operation(summary = "멤버별 프로젝트 분석")
	@PostMapping("/analytics/member")
	public CommonResponse<AnalyzeProjectByMemberResponse> analyzeProjectsByMember(@RequestBody
		AnalyzeProjectByMemberRequest request) {
		AnalyzeProjectByMemberCommand command = new AnalyzeProjectByMemberCommand(request.getProjectId());

		AnalyzeProjectByMemberResult result = analyzeProjectByMember.execute(command);

		AnalyzeProjectByMemberResponse response = new AnalyzeProjectByMemberResponse(
			result.members().stream().map(p -> AnalyzeProjectByMemberResponse.MemberStatistics.builder()
				.member(p.name())
				.issueStatistics(p.issueStatistics().stream().map(i ->
					AnalyzeProjectByMemberResponse.MemberStatistics.IssueStatistics.builder()
						.status(i.status())
						.count(i.count())
						.build()
				).toList())
				.build()
			).toList()
		);

		return CommonResponse.success("Project is analyzed by member", response);
	}
}
