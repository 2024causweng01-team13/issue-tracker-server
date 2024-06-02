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

		private final User manager = User.builder().id(1L).loginId("u1").name("username").password("pwd").build();

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

	@Nested
	@DisplayName("멤버 추가 테스트")
	class AddMemberTest {

		private final User manager = User.builder().id(1L).loginId("u1").name("username").password("pw").build();
		private final Project project = Project.builder()
			.title("project title")
			.description("project description")
			.manager(manager).build();

		@Test
		@DisplayName("멤버를 추가할 수 있다.")
		void test() {
			User member = User.builder().id(2L).loginId("u2").name("member").password("pwd").build();
			project.addMember(manager, member);

			assertTrue(project.getMemberships().stream().anyMatch(membership ->
				membership.getMember().equals(member)));
		}
	}

	@Nested
	@DisplayName("권한이 있는 멤버 확인 테스트")
	class IsAuthorizedTest {

		private final User manager = User.builder().id(1L).loginId("u1").name("username").password("pwd").build();
		private final Project project = Project.builder()
			.title("project title")
			.description("project description")
			.manager(manager).build();

		@Test
		@DisplayName("프로젝트의 매니저는 프로젝트에 권한이 있다.")
		void successOnManager() {
			assertTrue(project.isAuthorized(manager));
		}

		@Test
		@DisplayName("프로젝트의 멤버는 프로젝트에 권한이 있다.")
		void successOnMember() {
			User member = User.builder().id(2L).loginId("u2").name("member").password("pwd").build();
			project.addMember(manager, member);

			assertTrue(project.isAuthorized(member));
		}

		@Test
		@DisplayName("프로젝트의 매니저도, 멤버도 아닌 유저는 프로젝트에 권한이 없다.")
		void failOnNotManagerAndNotMember() {
			User user = User.builder().id(3L).loginId("u3").name("user").password("pwd").build();

			assertFalse(project.isAuthorized(user));
		}
	}
}