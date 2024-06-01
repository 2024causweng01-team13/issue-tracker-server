package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

public record AnalyzeProjectByMemberResult(
	List<MemberStatistics> members
) {

	@Builder
	public record MemberStatistics(
		String name,
		List<IssueStatistics> issueStatistics
	) {

		@Builder
		public record IssueStatistics(
			IssueStatus status,
			int count
		) {
		}
	}
}
