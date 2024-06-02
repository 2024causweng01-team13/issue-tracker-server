package org.causwengteam13.issuetrackerserver.domain.user.service;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

public interface TokenService {

	String createIdToken(User user);
	String validate(String token);
}
