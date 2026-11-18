package com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt;

import com.nortic.pos.ms.auth.application.port.output.TokenProvider;
import com.nortic.pos.ms.auth.domain.model.User;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt.constants.JwtConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey jwtSecret;
    private final String issuer;

    public JwtTokenProvider(
            @Value("${security.jwt.secret}") String jwtSecret,
            @Value("${spring.application.name}") String issuer) {

        this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        this.issuer = issuer;
    }

    @Override
    public String generateUserJwt(User user) {
        log.info("Generating JWT for user: {}", user.getId());

        Instant now = Instant.now();
        Instant expiration = now.plus(JwtConstants.EXPIRATION_HOURS, ChronoUnit.HOURS);

        log.info("Generating JWT for user: {} finished", user.getId());
        return Jwts.builder()
                .issuer(issuer)
                .subject(user.getId().toString())
                .claim(JwtConstants.STORE_ID, null)
                .claim(JwtConstants.BRANCH_ID, null)
                .claim(JwtConstants.PERMISSIONS, List.of("system.all"))
                .claim(JwtConstants.TYPE, JwtConstants.ACCESS_TYPE_TOKEN)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(jwtSecret, Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public String generateInternalJwt() {

        log.info("Generating internal JWT");

        Instant now = Instant.now();
        Instant expiration = now.plus(JwtConstants.EXPIRATION_HOURS, ChronoUnit.HOURS);

        log.info("Generating internal JWT finished");

        return Jwts.builder()
                .issuer(issuer)
                .subject(issuer)
                .claim(JwtConstants.STORE_ID, null)
                .claim(JwtConstants.BRANCH_ID, null)
                .claim(JwtConstants.PERMISSIONS, JwtConstants.INTERNAL_PERMISSIONS)
                .claim(JwtConstants.TYPE, JwtConstants.INTERNAL_TYPE_TOKEN)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(jwtSecret, Jwts.SIG.HS256)
                .compact();
    }
}
