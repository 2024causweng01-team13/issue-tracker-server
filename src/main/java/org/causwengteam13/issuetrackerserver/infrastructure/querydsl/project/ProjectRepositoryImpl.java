package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project;

import static org.causwengteam13.issuetrackerserver.domain.project.entity.QProject.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.causwengteam13.issuetrackerserver.domain.project.command.FindProjectsCommand;
import org.causwengteam13.issuetrackerserver.domain.project.entity.Project;
import org.causwengteam13.issuetrackerserver.domain.project.repository.ProjectRepositoryCustom;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Project> findProjects(FindProjectsCommand command) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(command.title())) {
			predicates.add(project.title.containsIgnoreCase(command.title()));
		}

		if (StringUtils.isNotBlank(command.description())) {
			predicates.add(project.description.containsIgnoreCase(command.description()));
		}

		return queryFactory.selectFrom(project)
			.leftJoin(project.manager).fetchJoin()
			.where(ExpressionUtils.allOf(predicates))
			.fetch();
	}
}
