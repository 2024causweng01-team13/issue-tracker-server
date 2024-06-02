package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project;

import static com.querydsl.core.group.GroupBy.*;
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
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.AnalyzeProjectByDateQuerydslResult;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.AnalyzeProjectByMemberQuerydslResult;
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

		List<AnalyzeProjectByDateQuerydslResult.DateStatistics> dateStatistics = queryFactory.select(
				Projections.constructor(AnalyzeProjectByDateQuerydslResult.DateStatistics.class,
					dateUnit,
					issue.count().as("count")
				))
			.from(issue)
			.where(ExpressionUtils.allOf(predicates))
			.groupBy(dateUnit)
			.fetch();

		return new AnalyzeProjectByDateResult(dateStatistics.stream().map(r ->
			AnalyzeProjectByDateResult.DateStatistics.builder()
				.date(r.date())
				.count(r.count())
				.build()
		).toList()
		);
	}

	@Override
	public AnalyzeProjectByMemberResult analyzeProjectByMember(AnalyzeProjectByMemberCommand command) {
		List<Predicate> predicates = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(command.getProjectId())) {
			predicates.add(issue.project.id.eq(command.getProjectId()));
		}

		List<AnalyzeProjectByMemberQuerydslResult.MemberStatistics> memberStatistics = queryFactory.select(
				Projections.constructor(AnalyzeProjectByMemberQuerydslResult.MemberStatistics.class,
					issue.assignee.name,
					list(Projections.constructor(AnalyzeProjectByMemberQuerydslResult.MemberStatistics.IssueStatistics.class,
						issue.status,
						issue.count().as("count")
					))
				))
			.from(issue)
			.where(ExpressionUtils.allOf(predicates))
			.groupBy(issue.assignee.name, issue.status)
			.fetch();

		return new AnalyzeProjectByMemberResult(memberStatistics.stream().map(r ->
			AnalyzeProjectByMemberResult.MemberStatistics.builder()
				.name(r.name())
				.issueStatistics(r.issueStatistics().stream().map(i ->
					AnalyzeProjectByMemberResult.MemberStatistics.IssueStatistics.builder()
						.status(i.status())
						.count(i.count())
						.build()
				).toList())
				.build()
		).toList()
		);
	}
}
