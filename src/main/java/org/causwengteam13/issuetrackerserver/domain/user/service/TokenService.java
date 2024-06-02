package org.causwengteam13.issuetrackerserver.domain.user.service;

public interface TokenService {

	String createIdToken(Long id);
	String validate(String token);
}
