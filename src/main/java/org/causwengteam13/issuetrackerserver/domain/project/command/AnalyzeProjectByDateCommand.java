package org.causwengteam13.issuetrackerserver.domain.project.command;

import java.time.LocalDateTime;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnalyzeProjectByDateCommand extends SelfValidator<AnalyzeProjectByDateCommand> {

	private final Long projectId;
	private final Unit unit;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;

	@Builder
	public AnalyzeProjectByDateCommand(Long projectId, Unit unit, LocalDateTime startDate, LocalDateTime endDate) {
		this.projectId = projectId;
		this.unit = unit;
		this.startDate = startDate;
		this.endDate = endDate;

		validateAndIfViolatedThrows();
	}

	public enum Unit {
		DAY, MONTH
	}
}
