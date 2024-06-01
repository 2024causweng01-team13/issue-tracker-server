package org.causwengteam13.issuetrackerserver.domain.user.command;

import org.causwengteam13.issuetrackerserver.common.SelfValidator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindUsersCommand extends SelfValidator<FindUsersCommand> {

	private final String name;
}
