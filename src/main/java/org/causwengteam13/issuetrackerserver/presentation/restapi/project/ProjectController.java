package org.causwengteam13.issuetrackerserver.presentation.restapi.project;

import org.causwengteam13.issuetrackerserver.domain.project.command.CreateProjectCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.CreateProjectResult;
import org.causwengteam13.issuetrackerserver.domain.project.usecase.CreateProject;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.request.CreateProjectRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.project.response.CreateProjectResponse;
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
}
