package org.causwengteam13.issuetrackerserver.presentation.restapi.project;

import org.causwengteam13.issuetrackerserver.domain.project.command.CreateProjectCommand;
import org.causwengteam13.issuetrackerserver.domain.project.command.FindProjectsCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.CreateProjectResult;
import org.causwengteam13.issuetrackerserver.domain.project.result.FindProjectsResult;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.CreateProject;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.FindProjects;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.CreateProjectRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.FindProjectsRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.CreateProjectResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.FindProjectsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

	private final CreateProject createProject;
	private final FindProjects findProjects;

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

	@PostMapping("/find")
	public CommonResponse<FindProjectsResponse> findProjects(@RequestBody FindProjectsRequest request) {
		FindProjectsCommand command = FindProjectsCommand.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.build();

		FindProjectsResult result = findProjects.execute(command);

		FindProjectsResponse response = FindProjectsResponse.from(result);

		return CommonResponse.success("Projects found", response);
	}
}
