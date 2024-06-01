package org.causwengteam13.issuetrackerserver.presentation.restapi.project.request;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByDateCommand;

import lombok.Getter;

@Getter
public class AnalyzeProjectByDateRequest {

	private Long projectId;
	private AnalyzeProjectByDateCommand.Unit unit;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
