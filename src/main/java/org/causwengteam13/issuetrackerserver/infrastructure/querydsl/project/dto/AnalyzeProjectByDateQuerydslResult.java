package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

public class AnalyzeProjectByDateQuerydslResult {

	public record DateStatistics(LocalDateTime date, Long count) {

		@QueryProjection
		public DateStatistics {
		}
	}
}
