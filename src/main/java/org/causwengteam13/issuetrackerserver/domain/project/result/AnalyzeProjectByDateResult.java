package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

public record AnalyzeProjectByDateResult(
	List<DateStatistics> dateStatistics
) {

	@Builder
	public record DateStatistics(
		LocalDateTime date,
		Long count
	) {
	}
}
