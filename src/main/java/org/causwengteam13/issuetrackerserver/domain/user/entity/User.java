package org.causwengteam13.issuetrackerserver.domain.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectMembership;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="users")
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String loginId;

	private String name;

	private String password;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<ProjectMembership> memberships = new ArrayList<>();

	@Builder
	private User(Long id, String loginId, String name, String password) {
		if (StringUtils.isBlank(loginId)) {
			throw new IllegalArgumentException("User loginId must not be blank");
		}

		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("User title must not be blank");
		}

		if (StringUtils.isBlank(password)) {
			throw new IllegalArgumentException("User password must not be blank");
		}

		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.password = password;
	}

	public boolean isAdmin() {
		return loginId.equals("admin");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User)o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
