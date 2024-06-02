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

		private final User manager = User.builder().id(1L).loginId("u1").name("manager").password("password").build();
		private final Project project = Project.builder().title("title").manager(manager).build();
		private final Issue issue = Issue.builder()
			.title("title")
			.description("description")
			.project(project)
			.reporter(User.builder().id(2L).loginId("m2").name("reporter").password("password").build())
			.build();

		@Test
		@DisplayName("작성자가 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈에 코멘트를 추가할 수 없다.")
		void fail() {
			assertThatThrownBy(() -> issue.addComment(User.builder().id(3L).loginId("u3").name("user1").password("password").build(), "content"))
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

		private static final String comment = "assign";
		private final User manager = User.builder().id(1L).loginId("u1").name("manager").password("password").build();
		private Project project;
		private Issue issue;

		@BeforeEach
		void setUp() {
			project = Project.builder().title("title").manager(manager).build();
			issue = Issue.builder()
				.title("title")
				.description("description")
				.project(project)
				.reporter(User.builder().id(2L).loginId("u2").name("reporter").password("pwd").build())
				.build();
		}

		@Test
		@DisplayName("할당하는 사람이 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈를 할당할 수 없다.")
		void failOnUnauthorizedAssigner() {
			User assigner = User.builder().id(3L).loginId("u3").name("assigner").password("pwd").build();
			User assignee = User.builder().id(4L).loginId("u4").name("assignee").password("pwd").build();

			assertThatThrownBy(() -> issue.assign(assigner, assignee, comment))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("할당받는 사람이 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈를 할당할 수 없다.")
		void failOnUnauthorizedAssignee() {
			User assigner = User.builder().id(3L).loginId("u3").name("assigner").password("pwd").build();
			User assignee = User.builder().id(4L).loginId("u4").name("assignee").password("pwd").build();

			project.addMember(manager, assigner);

			assertThatThrownBy(() -> issue.assign(assigner, assignee, comment))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("할당하는 사람과 할당받는 사람이 이슈를 포함하는 프로젝트의 멤버면 이슈를 할당할 수 있다.")
		void success() {
			User assigner = User.builder().id(3L).loginId("u3").name("assigner").password("pwd").build();
			User assignee = User.builder().id(4L).loginId("u4").name("assignee").password("pwd").build();

			project.addMember(manager, assigner);
			project.addMember(manager, assignee);

			assertDoesNotThrow(() -> issue.assign(assigner, assignee, comment));
			assertEquals(4L, issue.getAssignee().getId());
			assertThat(issue.getComments()).hasSize(1);
			assertThat(issue.getComments().get(0).getAuthor()).isEqualTo(assigner);
			assertThat(issue.getComments().get(0).getContent()).contains(comment);
			assertThat(issue.getStatus()).isEqualTo(IssueStatus.ASSIGNED);
		}
	}

	@Nested
	@DisplayName("이슈 해결 테스트")
	class FixTest {

		private static final String comment = "fix";
		private final User manager = User.builder().id(1L).loginId("u1").name("manager").password("password").build();
		private Project project;
		private Issue issue;

		@BeforeEach
		void setUp() {
			project = Project.builder().title("title").manager(manager).build();
			issue = Issue.builder()
				.title("title")
				.description("description")
				.project(project)
				.reporter(User.builder().id(2L).loginId("u2").name("reporter").password("pwd").build())
				.build();
		}

		@Test
		@DisplayName("버그를 해결하는 사람이 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈를 해결할 수 없다.")
		void fail() {
			User fixer = User.builder().id(3L).loginId("u3").name("fixer").password("pwd").build();

			assertThatThrownBy(() -> issue.fix(fixer, comment))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("버그를 해결하는 사람이 이슈를 포함하는 프로젝트의 멤버면 이슈를 해결할 수 있다.")
		void success() {
			User fixer = User.builder().id(5L).loginId("u2").name("fixer").password("password").build();

			project.addMember(manager, fixer);
			assertDoesNotThrow(() -> issue.fix(fixer, comment));

			assertEquals(5L, issue.getFixer().getId());
			assertThat(issue.getComments()).hasSize(1);
			assertThat(issue.getComments().get(0).getAuthor()).isEqualTo(fixer);
			assertThat(issue.getComments().get(0).getContent()).contains(comment);
			assertThat(issue.getStatus()).isEqualTo(IssueStatus.FIXED);
		}
	}

	@Nested
	@DisplayName("이슈 수정 테스트")
	class EditTest {

		private final String titleToChange = "changed title";
		private final String descriptionToChange = "changed description";
		private final IssuePriority priorityToChange = IssuePriority.BLOCKER;
		private final IssueStatus statusToChange = IssueStatus.ASSIGNED;

		private final User manager = User.builder().id(1L).loginId("u1").name("manager").password("password").build();
		private Project project;
		private Issue issue;

		@BeforeEach
		void setUp() {
			project = Project.builder().title("title").manager(manager).build();
			issue = Issue.builder()
				.title("title")
				.description("description")
				.project(project)
				.reporter(User.builder().id(2L).loginId("u2").name("reporter").password("pwd").build())
				.build();
		}

		@Test
		@DisplayName("이슈를 수정하는 사람이 이슈를 포함하는 프로젝트의 멤버가 아니면 이슈를 수정할 수 없다.")
		void fail() {
			User editor = User.builder().id(3L).loginId("u3").name("editor").password("pwd").build();

			assertThatThrownBy(() -> issue.edit(editor, titleToChange, descriptionToChange, priorityToChange, statusToChange))
				.isInstanceOf(UserUnauthorizedInProjectProblem.class);
		}

		@Test
		@DisplayName("이슈를 수정하는 사람이 이슈를 포함하는 프로젝트의 멤버면 이슈를 수정할 수 있다.")
		void success() {
			User editor = User.builder().id(3L).loginId("u3").name("editor").password("pwd").build();

			project.addMember(manager, editor);
			assertDoesNotThrow(() -> issue.edit(editor, titleToChange, descriptionToChange, priorityToChange, statusToChange));

			assertEquals(titleToChange, issue.getTitle());
			assertEquals(descriptionToChange, issue.getDescription());
			assertEquals(priorityToChange, issue.getPriority());
			assertEquals(statusToChange, issue.getStatus());
		}
	}
}