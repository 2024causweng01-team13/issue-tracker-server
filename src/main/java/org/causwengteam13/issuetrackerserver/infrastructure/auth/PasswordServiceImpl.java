package org.causwengteam13.issuetrackerserver.infrastructure.auth;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.domain.user.service.EncodePasswordService;
import org.causwengteam13.issuetrackerserver.domain.user.service.VerifyPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements EncodePasswordService, VerifyPasswordService {

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean verifyPassword(User user, String rawPassword) {
		return passwordEncoder.matches(rawPassword, user.getPassword());
	}
}
