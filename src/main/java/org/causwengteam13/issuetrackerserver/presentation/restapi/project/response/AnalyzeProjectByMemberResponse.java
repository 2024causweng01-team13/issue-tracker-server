package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;

import lombok.Builder;

public record AnalyzeProjectByMemberResponse(
	List<MemberStatistics> memberStatistics
) {

	@Builder
	public record MemberStatistics(
		String member,
		List<IssueStatistics> issueStatistics
	) {

		@Builder
		public record IssueStatistics(
			IssueStatus status,
			Long count
		) {
		}
	}
}
