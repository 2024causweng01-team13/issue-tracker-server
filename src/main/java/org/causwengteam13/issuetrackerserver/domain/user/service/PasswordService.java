package org.causwengteam13.issuetrackerserver.domain.user.service;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;

public interface PasswordService {

	String encode(String rawPassword);
	boolean isCorrect(User user, String rawPassword);
}
