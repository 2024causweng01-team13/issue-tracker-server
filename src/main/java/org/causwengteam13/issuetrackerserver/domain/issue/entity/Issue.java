package org.causwengteam13.issuetrackerserver.domain.issue.entity;

import java.util.ArrayList;
import java.util.List;

import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.UserNotAuthorizedInProjectProblem;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "parentIssue", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	public Issue(Project project) {
		this.project = project;
	}

	public Comment addComment(User author, String content) {
		if (!project.containsUser(author)) {
			throw new UserNotAuthorizedInProjectProblem();
		}

		Comment comment = Comment.builder()
			.author(author)
			.content(content)
			.parentIssue(this)
			.build();

		this.comments.add(comment);

		return comment;
	}
}
