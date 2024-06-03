package org.causwengteam13.issuetrackerserver.domain.project.usecase;

import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByMemberCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByMemberResult;
import org.causwengteam13.issuetrackerserver.domain.project.service.AnalyzeProjectByMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnalyzeProjectByMember {

	private final AnalyzeProjectByMemberService analyzeProjectByMemberService;

	public AnalyzeProjectByMemberResult execute(AnalyzeProjectByMemberCommand command) {
		return analyzeProjectByMemberService.analyzeProjectByMember(command);
	}
}
