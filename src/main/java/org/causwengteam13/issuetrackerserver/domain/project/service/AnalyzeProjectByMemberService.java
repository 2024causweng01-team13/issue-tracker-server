package org.causwengteam13.issuetrackerserver.domain.project.service;

import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByMemberCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByMemberResult;

public interface AnalyzeProjectByMemberService {
	AnalyzeProjectByMemberResult analyzeProjectByMember(AnalyzeProjectByMemberCommand command);
}
