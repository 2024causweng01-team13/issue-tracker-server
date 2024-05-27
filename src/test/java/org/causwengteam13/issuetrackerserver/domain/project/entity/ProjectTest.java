package org.causwengteam13.issuetrackerserver.domain.project.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProjectTest {

	@Nested
	@DisplayName("생성 테스트")
	class CreateTest {

		private final User manager = User.builder().id(1L).name("username").build();

		@Test
		@DisplayName("매니저가 null이면 프로젝트를 생성할 수 없다.")
		void failOnManagerNull() {
			assertThatThrownBy(() -> Project.of(null, "title", " "))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("제목이 비어 있으면 프로젝트를 생성할 수 없다.")
		void failOnTitleBlank() {
			assertThatThrownBy(() -> Project.of(manager, " ", "description"))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("매니저와 제목이 모두 있으면 프로젝트를 생성할 수 있다.")
		void test() {
			assertDoesNotThrow(() -> Project.of(manager, "title", "description"));
		}
	}
}