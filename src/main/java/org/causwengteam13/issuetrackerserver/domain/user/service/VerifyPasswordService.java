package org.causwengteam13.issuetrackerserver.domain.user.service;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

public interface VerifyPasswordService {
	boolean verifyPassword(User user, String rawPassword);
}
