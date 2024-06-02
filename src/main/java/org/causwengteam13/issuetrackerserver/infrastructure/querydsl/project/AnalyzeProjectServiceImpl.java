package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project;

import static com.querydsl.core.group.GroupBy.*;
import static org.causwengteam13.issuetrackerserver.domain.issue.entity.QIssue.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByDateCommand;
import org.causwengteam13.issuetrackerserver.domain.project.command.AnalyzeProjectByMemberCommand;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByDateResult;
import org.causwengteam13.issuetrackerserver.domain.project.result.AnalyzeProjectByMemberResult;
import org.causwengteam13.issuetrackerserver.domain.project.service.AnalyzeProjectService;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.AnalyzeProjectByMemberQuerydslResult;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyzeProjectServiceImpl implements AnalyzeProjectService {

	private final JPAQueryFactory queryFactory;
	private final EntityManager entityManager;

	@Override
	public AnalyzeProjectByDateResult analyzeProjectByDate(AnalyzeProjectByDateCommand command) {
		// 문제 해결이 안 되어 당분간 raw query로 대체
		// List<Predicate> predicates = new ArrayList<>();
		// if (ObjectUtils.isNotEmpty(command.getProjectId())) {
		// 	predicates.add(issue.project.id.eq(command.getProjectId()));
		// }
		//
		// if (ObjectUtils.isNotEmpty(command.getStartDate())) {
		// 	predicates.add(issue.createdAt.goe(command.getStartDate()));
		// }
		//
		// if (ObjectUtils.isNotEmpty(command.getEndDate())) {
		// 	predicates.add(issue.createdAt.loe(command.getEndDate()));
		// }

		// DateTemplate dateUnit = switch (command.getUnit()) {
		// 	case DAY -> Expressions.dateTemplate(LocalDateTime.class, "DATE_FORMAT({0}, {1})", issue.createdAt, "%Y-%m-%d");
		// 	case MONTH -> Expressions.dateTemplate(LocalDateTime.class, "DATE_FORMAT({0}, {1})", issue.createdAt, "%Y-%m");
		// };
		//
		// List<AnalyzeProjectByDateQuerydslResult.DateStatistics> dateStatistics = queryFactory.select(
		// 		Projections.constructor(AnalyzeProjectByDateQuerydslResult.DateStatistics.class,
		// 			dateUnit,
		// 			issue.count().as("count")
		// 		))
		// 	.from(issue)
		// 	.where(ExpressionUtils.allOf(predicates))
		// 	.groupBy(dateUnit)
		// 	.fetch();
		//
		// return new AnalyzeProjectByDateResult(dateStatistics.stream().map(r ->
		// 	AnalyzeProjectByDateResult.DateStatistics.builder()
		// 		.date(r.date())
		// 		.count(r.count())
		// 		.build()
		// 	).toList()
		// );
		String dateFormat = switch (command.getUnit()) {
			case DAY -> "%Y-%m-%d";
			case MONTH -> "%Y-%m";
		};

		String sql = """
			select
			        date_format(i1_0.created_at, :dateFormat) as 'date',
			        count(i1_0.id) as 'count'
			    from
			        issues i1_0
			    where
			        i1_0.project_id=:projectId
			        and i1_0.created_at between :startDate and :endDate
			    group by
			        date_format(i1_0.created_at, '%Y-%m-%d')
			""";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("dateFormat", dateFormat);
		query.setParameter("projectId", command.getProjectId());
		query.setParameter("startDate", command.getStartDate());
		query.setParameter("endDate", command.getEndDate());

		List<Object[]> resultList = query.getResultList();

		return new AnalyzeProjectByDateResult(resultList.stream().map(r ->
			AnalyzeProjectByDateResult.DateStatistics.builder()
				.date(r[0].toString())
				.count(Long.parseLong(r[1].toString()))
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

		Map<String, List<AnalyzeProjectByMemberQuerydslResult.MemberStatistics.IssueStatistics>> result = queryFactory.select(
				Projections.constructor(AnalyzeProjectByMemberQuerydslResult.MemberStatistics.class,
					issue.assignee.name,
					list(
						Projections.constructor(AnalyzeProjectByMemberQuerydslResult.MemberStatistics.IssueStatistics.class,
							issue.status,
							issue.count().as("count")
						))
				))
			.from(issue)
			.where(ExpressionUtils.allOf(predicates))
			.groupBy(issue.assignee.name, issue.status)
			.transform(groupBy(issue.assignee.name).as(list(
				Projections.constructor(AnalyzeProjectByMemberQuerydslResult.MemberStatistics.IssueStatistics.class,
					issue.status,
					issue.count().as("count")
				))));

		return new AnalyzeProjectByMemberResult(result.entrySet().stream().map(e ->
			AnalyzeProjectByMemberResult.MemberStatistics.builder()
				.name(e.getKey())
				.issueStatistics(e.getValue().stream().map(i ->
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
