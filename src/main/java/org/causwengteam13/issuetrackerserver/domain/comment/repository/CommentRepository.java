package org.causwengteam13.issuetrackerserver.domain.comment.repository;

import org.causwengteam13.issuetrackerserver.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
