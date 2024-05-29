package org.causwengteam13.issuetrackerserver.presentation.restapi.comment;

import org.causwengteam13.issuetrackerserver.domain.comment.command.CreateCommentCommand;
import org.causwengteam13.issuetrackerserver.domain.comment.result.CreateCommentResult;
import org.causwengteam13.issuetrackerserver.domain.comment.usecase.CreateComment;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.comment.request.CreateCommentRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.comment.response.CreateCommentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

	private final CreateComment createComment;

	@PostMapping
	public CommonResponse<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request) {
		CreateCommentCommand command = CreateCommentCommand.builder()
			.authorId(request.getAuthorId())
			.parentIssueId(request.getParentIssueId())
			.content(request.getContent())
			.build();

		CreateCommentResult result = createComment.execute(command);

		CreateCommentResponse response = CreateCommentResponse.builder()
			.id(result.id())
			.authorName(result.authorName())
			.content(result.content())
			.createdAt(result.createdAt())
			.updatedAt(result.updatedAt())
			.build();

		return CommonResponse.success("Create Comment success", response);
	}
}
