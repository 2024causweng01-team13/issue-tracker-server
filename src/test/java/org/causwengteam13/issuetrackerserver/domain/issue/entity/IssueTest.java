package org.causwengteam13.issuetrackerserver.domain.issue.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.UserUnauthorizedInProjectProblem;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class IssueTest {

	@Nested
	@DisplayName("코멘트 추가 테스트")
	class addCommentTest {

		private final User manager = User.builder().id(1L).name("username").build();
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
}