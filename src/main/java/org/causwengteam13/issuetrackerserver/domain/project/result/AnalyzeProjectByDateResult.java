package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.util.List;

public record AnalyzeProjectByDateResult(
	List<DateStatistics> dateStatistics
) {

	public record DateStatistics(
		String date,
		Integer count
	) {
	}
}
