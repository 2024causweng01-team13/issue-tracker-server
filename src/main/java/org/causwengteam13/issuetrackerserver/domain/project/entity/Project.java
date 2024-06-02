package org.causwengteam13.issuetrackerserver.domain.project.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private User manager;

	private String title;

	private String description;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(255)")
	private ProjectStatus status = ProjectStatus.OPEN;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Issue> issues = new ArrayList<>();

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ProjectMembership> memberships = new ArrayList<>();

	@Builder
	private Project(User manager, String title, String description) {
		if (manager == null) {
			throw new IllegalArgumentException("Project manager must not be null");
		}

		if (StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("Project title must not be blank");
		}

		this.manager = manager;
		this.title = title;
		this.description = description;
	}

	public static Project of(User manager, String title, String description) {
		return Project.builder()
			.manager(manager)
			.title(title)
			.description(description)
			.build();
	}

	public void addMember(User user) {
		memberships.add(ProjectMembership.of(this, user));
	}

	public Boolean isAuthorized(User user) {
		return user.isAdmin() ||
			manager.equals(user) ||
			memberships.stream().anyMatch(membership -> membership.getMember().equals(user));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Project project = (Project)o;
		return Objects.equals(id, project.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
