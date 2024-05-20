package org.causwengteam13.issuetrackerserver.domain.issue.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.UserNotAuthorizedInProjectProblem;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class IssueTest {

	@Nested
	@DisplayName("addComment Test")
	class addCommentTest {

		private Issue issue;

		@Nested
		@DisplayName("작성자가 이슈를 포함하는 프로젝트의 멤버가 아니면")
		class AuthorInProject {

			@BeforeEach
			void setUp() {
				Project project = Project.builder()
					.users(List.of())
					.build();
				issue = new Issue(project);
			}

			@Test
			@DisplayName("이슈에 코멘트를 추가할 수 없다.")
			void fail() {
				assertThatThrownBy(() -> issue.addComment(new User(1L), "content"))
					.isInstanceOf(UserNotAuthorizedInProjectProblem.class);
			}
		}

		@Nested
		@DisplayName("작성자가 이슈를 포함하는 프로젝트에 권한이 있으면")
		class AuthorNotInProject {

			@BeforeEach
			void setUp() {
				Project project = Project.builder()
					.users(List.of(new User(1L)))
					.build();
				issue = new Issue(project);
			}

			@Test
			@DisplayName("이슈에 코멘트를 추가할 수 있다.")
			void success() {
				Comment comment = issue.addComment(new User(1L), "content");

				assertNotNull(comment);
				assertEquals("content", comment.getContent());
			}
		}
	}
}