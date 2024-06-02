package org.causwengteam13.issuetrackerserver.domain.issue.usecase;

import lombok.RequiredArgsConstructor;
import org.causwengteam13.issuetrackerserver.domain.issue.command.CreateIssueCommand;
import org.causwengteam13.issuetrackerserver.domain.issue.entity.Issue;
import org.causwengteam13.issuetrackerserver.domain.issue.repository.IssueRepository;
import org.causwengteam13.issuetrackerserver.domain.issue.result.CreateIssueResult;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.problem.ProjectNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.project.repository.ProjectRepository;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.problem.UserNotFoundProblem;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateIssue {
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    public CreateIssueResult execute(CreateIssueCommand command) {
        User reporter = userRepository.findById(command.getReporterId())
                .orElseThrow(() -> new UserNotFoundProblem(command.getReporterId()));

        Project project = projectRepository.findById(command.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundProblem(command.getProjectId()));

        Issue issue = Issue.of(reporter, command.getTitle(), command.getDescription(), project);
        Issue savedIssue = issueRepository.save(issue);

        return new CreateIssueResult(savedIssue);
    }
}
