package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.util.List;

import lombok.Builder;

public record AnalyzeProjectByDateResult(
	List<DateStatistics> dateStatistics
) {

	@Builder
	public record DateStatistics(
		String date,
		Long count
	) {
	}
}
