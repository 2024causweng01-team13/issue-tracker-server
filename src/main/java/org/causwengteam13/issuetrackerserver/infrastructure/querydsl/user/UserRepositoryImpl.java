package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.user;

import static org.causwengteam13.issuetrackerserver.domain.user.entity.QUser.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.causwengteam13.issuetrackerserver.domain.user.command.FindUsersCommand;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<User> findUsersByCommand(FindUsersCommand command) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotBlank(command.getName())) {
			predicates.add(user.name.containsIgnoreCase(command.getName()));
		}

		return jpaQueryFactory.selectFrom(user)
			.where(ExpressionUtils.allOf(predicates))
			.fetch();
	}
}
