package org.causwengteam13.issuetrackerserver.domain.issue.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.UserUnauthorizedInProjectProblem;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class IssueTest {

	@Nested
	@DisplayName("코멘트 추가 테스트")
	class addCommentTest {

		private final User manager = User.builder().id(1L).name("manager").build();
		private final Project project = Project.builder().title("title").manager(manager).build();
		private final Issue issue = Issue.builder()
			.title("title")
			.description("description")
			.project(project)
			.reporter(User.builder().id(2L).name("reporter").build())
			.build();

		@Test
		@DisplayName("작성자가 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈에 코멘트를 추가할 수 없다.")
		void fail() {
			assertThatThrownBy(() -> issue.addComment(User.builder().id(3L).name("user1").build(), "content"))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("작성자가 이슈를 포함하는 프로젝트의 멤버면 이슈에 코멘트를 추가할 수 있다.")
		void success() {
			Comment comment = issue.addComment(manager, "content");

			assertNotNull(comment);
			assertEquals("content", comment.getContent());
		}
	}

	@Nested
	@DisplayName("이슈 할당 테스트")
	class assignTest {

		private final User manager = User.builder().id(1L).name("manager").build();
		private Project project;
		private Issue issue;

		@BeforeEach
		void setUp() {
			project = Project.builder().title("title").manager(manager).build();
			issue = Issue.builder()
				.title("title")
				.description("description")
				.project(project)
				.reporter(User.builder().id(2L).name("reporter").build())
				.build();
		}

		@Test
		@DisplayName("할당하는 사람이 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈를 할당할 수 없다.")
		void failOnUnauthorizedAssigner() {
			User assigner = User.builder().id(3L).name("assigner").build();
			User assignee = User.builder().id(4L).name("assignee").build();

			assertThatThrownBy(() -> issue.assign(assigner, assignee))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("할당받는 사람이 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈를 할당할 수 없다.")
		void failOnUnauthorizedAssignee() {
			User assigner = User.builder().id(3L).name("assigner").build();
			User assignee = User.builder().id(4L).name("assignee").build();

			project.addMember(assigner);

			assertThatThrownBy(() -> issue.assign(assigner, assignee))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("할당하는 사람과 할당받는 사람이 이슈를 포함하는 프로젝트의 멤버면 이슈를 할당할 수 있다.")
		void success() {
			User assigner = User.builder().id(3L).name("assigner").build();
			User assignee = User.builder().id(4L).name("assignee").build();

			project.addMember(assigner);
			project.addMember(assignee);

			assertDoesNotThrow(() -> issue.assign(assigner, assignee));
			assertEquals(4L, issue.getAssignee().getId());
			assertThat(issue.getComments()).hasSize(1);
			assertThat(issue.getComments().get(0).getAuthor()).isEqualTo(assigner);
		}
	}
}