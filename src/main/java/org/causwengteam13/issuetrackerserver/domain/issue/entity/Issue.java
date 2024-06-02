package org.causwengteam13.issuetrackerserver.domain.issue.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.UserUnauthorizedInProjectProblem;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issues")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "reporter_id")
	private User reporter;

	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private User assignee;

	@ManyToOne
	@JoinColumn(name = "fixer_id")
	private User fixer;

	@OneToMany(mappedBy = "parentIssue", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(255)")
	private IssuePriority priority = IssuePriority.MAJOR;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(255)")
	private IssueStatus status = IssueStatus.NEW;

	@Builder
	private Issue(String title, String description, Project project, User reporter, User assignee, IssuePriority priority) {
		if (project == null) {
			throw new IllegalArgumentException("Issue project must not be null");
		}

		if (reporter == null) {
			throw new IllegalArgumentException("Issue reporter must not be null");
		}

		if (StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("Issue title must not be blank");
		}

		this.title = title;
		this.description = description;
		this.project = project;
		this.reporter = reporter;
		this.assignee = assignee;
		this.priority = priority;
		this.status = assignee == null ? IssueStatus.NEW : IssueStatus.ASSIGNED;
	}


	public static Issue of(User reporter, String title, String description,  Project project) {
		return Issue.builder()
				.reporter(reporter)
				.title(title)
				.description(description)
				.project(project)
				.build();
	}

	public Comment addComment(User author, String content) {
		if (!project.isAuthorized(author)) {
			throw new UserUnauthorizedInProjectProblem(author.getId());
		}

		Comment comment = Comment.builder()
			.author(author)
			.content(content)
			.parentIssue(this)
			.build();

		this.comments.add(comment);

		return comment;
	}

	public void assign(User assigner, User assignee, String comment) {
		if (!project.isAuthorized(assigner)) {
			throw new UserUnauthorizedInProjectProblem(assigner.getId());
		}

		if (!project.isAuthorized(assignee)) {
			throw new UserUnauthorizedInProjectProblem(assignee.getId());
		}

		this.assignee = assignee;
		this.status = IssueStatus.ASSIGNED;
		addComment(assigner, String.format("Assigned to %s\n%s", assignee.getName(), comment));
	}

	public void fix(User fixer, String comment) {
		if (!project.isAuthorized(fixer)) {
			throw new UserUnauthorizedInProjectProblem(fixer.getId());
		}

		this.fixer = fixer;
		this.status = IssueStatus.RESOLVED;
		addComment(fixer, String.format("Fixed by %s\n%s", fixer.getName(), comment));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Issue issue = (Issue)o;
		return Objects.equals(id, issue.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
