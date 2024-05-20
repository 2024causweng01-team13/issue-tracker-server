package org.causwengteam13.issuetrackerserver.domain.project.entity;

import java.util.ArrayList;
import java.util.List;

import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Issue> issues = new ArrayList<>();

	@OneToMany(mappedBy = "project")
	private List<User> users = new ArrayList<>();

	@Builder
	public Project(Long id, List<Issue> issues, List<User> users) {
		this.id = id;
		this.issues = issues;
		this.users = users;
	}

	public boolean containsUser(User user) {
		return users.contains(user);
	}
}
