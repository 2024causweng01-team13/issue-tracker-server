package org.causwengteam13.issuetrackerserver.infrastructure.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.causwengteam13.issuetrackerserver.domain.user.problem.AuthFailedProblem;
import org.causwengteam13.issuetrackerserver.domain.user.service.TokenService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	private String secretKey = "sskey";

	@Override
	public String createIdToken(Long id) {
		Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
		String id_srt = String.valueOf(id);
		String jwt = Jwts.builder()
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.setSubject(id_srt).setIssuedAt(new Date()).setExpiration(expiredDate)
			.compact();

		return jwt;
	}

	@Override
	public String validate(String token) {
		Claims claims = null;

		try {
			claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new AuthFailedProblem(exception);
		}

		return claims.getSubject();
	}
}