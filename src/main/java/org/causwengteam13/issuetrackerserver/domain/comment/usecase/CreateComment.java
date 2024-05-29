package org.causwengteam13.issuetrackerserver.domain.comment.usecase;

import org.causwengteam13.issuetrackerserver.domain.comment.command.CreateCommentCommand;
import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.causwengteam13.issuetrackerserver.domain.comment.repository.CommentRepository;
import org.causwengteam13.issuetrackerserver.domain.comment.result.CreateCommentResult;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.problem.IssueNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateComment {

	private final UserRepository userRepository;
	private final IssueRepository issueRepository;
	private final CommentRepository commentRepository;

	public CreateCommentResult execute(CreateCommentCommand command) {
		User author = userRepository.findById(command.getAuthorId())
			.orElseThrow(() -> new UserNotFoundProblem(command.getAuthorId()));
		Issue issue = issueRepository.findById(command.getParentIssueId())
			.orElseThrow(() -> new IssueNotFoundProblem(command.getParentIssueId()));

		Comment comment = issue.addComment(author, command.getContent());
		Comment savedComment = commentRepository.save(comment);

		return CreateCommentResult.builder()
			.id(savedComment.getId())
			.authorName(savedComment.getAuthor().getName())
			.content(savedComment.getContent())
			.createdAt(savedComment.getCreatedAt())
			.updatedAt(savedComment.getUpdatedAt())
			.build();
	}
}
