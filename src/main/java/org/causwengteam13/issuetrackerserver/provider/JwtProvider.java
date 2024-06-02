package org.causwengteam13.issuetrackerserver.provider;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.Instant;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

@Component
public class JwtProvider {

    private String secretKey = "sskey";

    public String create(Long id) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        String id_srt = String.valueOf(id);
        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setSubject(id_srt).setIssuedAt(new Date()).setExpiration(expiredDate)
            .compact();
        return jwt;
    }

    public String validate(String jwt) {
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(jwt).getBody();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }
}
