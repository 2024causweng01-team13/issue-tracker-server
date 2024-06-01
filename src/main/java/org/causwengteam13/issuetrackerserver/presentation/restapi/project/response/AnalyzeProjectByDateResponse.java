package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

public record AnalyzeProjectByDateResponse(
	List<DateStatistics> dateStatistics
) {

	@Builder
	public record DateStatistics(
		LocalDateTime date,
		Long count
	) {
	}
}
