package org.causwengteam13.issuetrackerserver.domain.project.result;

import java.time.LocalDateTime;
import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record FindProjectByIdResult(
	Long id,
	String title,
	String description,
	String managerName,
	ProjectStatus status,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	List<MemberResult> members
) {

	@Builder
	public record MemberResult(
		Long id,
		String name
	) {
	}
}
