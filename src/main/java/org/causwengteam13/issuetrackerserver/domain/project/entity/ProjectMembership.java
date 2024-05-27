package org.causwengteam13.issuetrackerserver.domain.project.entity;

import java.time.ZonedDateTime;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_memberships")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectMembership {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private User member;

	private ZonedDateTime joinedAt = ZonedDateTime.now();

	public static ProjectMembership of(Project project, User member) {
		return ProjectMembership.builder()
			.project(project)
			.member(member)
			.build();
	}
}
