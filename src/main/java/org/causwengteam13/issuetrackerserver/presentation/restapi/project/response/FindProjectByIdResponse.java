package org.causwengteam13.issuetrackerserver.presentation.restapi.project.response;

import java.time.LocalDateTime;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record FindProjectByIdResponse(
	Long id,
	String title,
	String description,
	String managerName,
	ProjectStatus status,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	List<MemberResponse> members
) {

	@Builder
	public record MemberResponse(
		Long id,
		String name
	) {
	}
}
