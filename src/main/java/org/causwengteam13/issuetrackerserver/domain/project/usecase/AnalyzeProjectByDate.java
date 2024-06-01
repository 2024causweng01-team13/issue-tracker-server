package org.causwengteam13.issuetrackerserver.domain.project.usecase;

import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByDateCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByDateResult;
import org.causwengteam13.issuetrackerserver.domain.project.service.AnalyzeProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnalyzeProjectByDate {

	private final AnalyzeProjectService analyzeProjectService;

	public AnalyzeProjectByDateResult execute(AnalyzeProjectByDateCommand command) {
		return analyzeProjectService.analyzeProjectByDate(command);
	}
}
