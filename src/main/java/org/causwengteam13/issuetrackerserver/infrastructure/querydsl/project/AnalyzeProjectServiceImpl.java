package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project;

import static org.causwengteam13.issuetrackerserver.domain.issue.entity.QIssue.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByDateCommand;
import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByMemberCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByDateResult;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByMemberResult;
import org.causwengteam13.issuetrackerserver.domain.project.service.AnalyzeProjectService;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyzeProjectServiceImpl implements AnalyzeProjectService {

	private final JPAQueryFactory queryFactory;

	@Override
	public AnalyzeProjectByDateResult analyzeProjectByDate(AnalyzeProjectByDateCommand command) {
		List<Predicate> predicates = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(command.getProjectId())) {
			predicates.add(issue.project.id.eq(command.getProjectId()));
		}

		if (ObjectUtils.isNotEmpty(command.getStartDate())) {
			predicates.add(issue.createdAt.goe(command.getStartDate()));
		}

		if (ObjectUtils.isNotEmpty(command.getEndDate())) {
			predicates.add(issue.createdAt.loe(command.getEndDate()));
		}

		DateTemplate dateUnit = switch (command.getUnit()) {
			case DAY -> Expressions.dateTemplate(LocalDateTime.class, "DATE_FORMAT({0}, {1})", issue.createdAt, "%Y-%m-%d");
			case MONTH -> Expressions.dateTemplate(LocalDateTime.class, "DATE_FORMAT({0}, {1})", issue.createdAt, "%Y-%m");
		};

		return new AnalyzeProjectByDateResult(queryFactory.select(
			Projections.constructor(AnalyzeProjectByDateResult.DateStatistics.class,
				dateUnit,
				issue.count().as("count")
			))
			.from(issue)
			.leftJoin(issue.project).fetchJoin()
			.where(ExpressionUtils.allOf(predicates))
			.groupBy(Expressions.stringTemplate("DATE_FORMAT({0}, {1})", issue.createdAt, "%Y-%m"))
			.fetch());
	}

	@Override
	public AnalyzeProjectByMemberResult analyzeProjectByMember(AnalyzeProjectByMemberCommand command) {
		List<Predicate> predicates = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(command.getProjectId())) {
			predicates.add(issue.project.id.eq(command.getProjectId()));
		}

		return new AnalyzeProjectByMemberResult(queryFactory.select(
				Projections.constructor(AnalyzeProjectByMemberResult.MemberStatistics.class,
					issue.assignee.name,
					Projections.constructor(AnalyzeProjectByMemberResult.MemberStatistics.IssueStatistics.class,
						issue.status,
						issue.count().as("count")
					)
				))
			.from(issue)
			.leftJoin(issue.project).fetchJoin()
			.where(ExpressionUtils.allOf(predicates))
			.groupBy(issue.assignee.name, issue.status)
			.fetch());
	}
}
