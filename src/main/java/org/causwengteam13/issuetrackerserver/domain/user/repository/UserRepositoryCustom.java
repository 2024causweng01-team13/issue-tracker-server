package org.causwengteam13.issuetrackerserver.domain.user.repository;

import java.util.List;

import org.causwengteam13.issuetrackerserver.domain.user.command.FindUsersCommand;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

public interface UserRepositoryCustom {

	List<User> findUsersByCommand(FindUsersCommand command);
}
