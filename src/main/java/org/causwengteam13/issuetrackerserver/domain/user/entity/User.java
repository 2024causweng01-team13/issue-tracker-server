package org.causwengteam13.issuetrackerserver.domain.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.entity.ProjectMembership;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignUpRequestDto;

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
	private Long id;
	private String password;
	private String name;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<ProjectMembership> memberships = new ArrayList<>();

	@Builder
	private User(Long id, String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("User title must not be blank");
		}

		this.id = id;
		this.name = name;
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

	public User(SignUpRequestDto dto) {
        this.id = dto.getId();
        this.password = dto.getPassword();
        this.name = dto.getName();
    }
}
