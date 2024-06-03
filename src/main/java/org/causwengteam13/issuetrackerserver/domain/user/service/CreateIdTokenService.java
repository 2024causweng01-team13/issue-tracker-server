package org.causwengteam13.issuetrackerserver.domain.user.service;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

public interface CreateIdTokenService {
	String createIdToken(User user);
}
