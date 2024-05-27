package org.causwengteam13.issuetrackerserver.domain.issue.repository;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
