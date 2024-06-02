package org.causwengteam13.issuetrackerserver.domain.issue.repository;

import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProjectId(Long projectId);
    @Query("SELECT i FROM Issue i WHERE i.assignee.name = :name")
    List<Issue> findByAssigneeName(@Param("name") String name);
    @Query("SELECT i FROM Issue i WHERE i.reporter.name = :name")
    List<Issue> findByReporterName(@Param("name") String name);
    @Query("SELECT i FROM Issue i WHERE i.status = :status")
    List<Issue> findByStatus(@Param("status") String status);
}
