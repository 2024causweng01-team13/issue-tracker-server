package org.causwengteam13.issuetrackerserver.domain.comment.entity;

import org.causwengteam13.issuetrackerserver.common.AbstractEntity;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_issue_id")
	private Issue parentIssue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;

	@Builder
	private Comment(String content, Issue parentIssue, User author) {
		this.content = content;
		this.parentIssue = parentIssue;
		this.author = author;
	}
}
