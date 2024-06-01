package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import com.querydsl.core.annotations.QueryProjection;

public class AnalyzeProjectByMemberQuerydslResult {

	public record MemberStatistics(
		String name,
		List<IssueStatistics> issueStatistics
	) {

		@QueryProjection
		public MemberStatistics {
		}

		public record IssueStatistics(
			IssueStatus status,
			Long count
		) {

			@QueryProjection
			public IssueStatistics {
			}
		}
	}
}
