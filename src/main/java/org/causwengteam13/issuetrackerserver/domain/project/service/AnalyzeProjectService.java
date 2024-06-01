package org.causwengteam13.issuetrackerserver.domain.project.service;

import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByDateCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByDateResult;

public interface AnalyzeProjectService {

	AnalyzeProjectByDateResult analyzeProjectByDate(AnalyzeProjectByDateCommand command);
}
