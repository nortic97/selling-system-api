package com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nortic.pos.ms.auth.application.port.output.TokenProvider;
import com.nortic.pos.ms.auth.domain.model.Employee;
import com.nortic.pos.ms.auth.domain.model.User;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt.constants.JwtConstants;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt.dto.JwtClaims;
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
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class JwtTokenProvider implements TokenProvider {

    private final String issuer;
    private final SecretKey jwtSecret;
    private final List<String> userPermissions;
    private final List<String> employeePermissions;
    private final ObjectMapper objectMapper;

    public JwtTokenProvider(
            @Value("${security.jwt.secret}") String jwtSecret,
            @Value("${spring.application.name}") String issuer,
            ObjectMapper objectMapper,
            @Value("${security.owner.permissions}") List<String> userPermissions,
            @Value("${security.employee.permissions}") List<String> employeePermissions) {

        this.issuer = issuer;
        this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        this.userPermissions = userPermissions;
        this.employeePermissions = employeePermissions;
        this.objectMapper = objectMapper;
    }

    @Override
    public String generateUserJwt(User user) {
        log.info("Generating JWT for user: {}", user.getId());

        var expiration = Instant.now().plus(JwtConstants.ACCESS_EXPIRATION_MINUTES, ChronoUnit.MINUTES);

        var claims = JwtClaims.builder()
                .permissions(userPermissions)
                .type(JwtConstants.ACCESS_TYPE_TOKEN)
                .build();

        log.info("Generating JWT for user: {} finished", user.getId());

        return generateJwt(user.getId().toString(), claims, expiration);
    }

    @Override
    public String generateEmployeeJwt(Employee employee) {

        log.info("Generating JWT for employee: {}", employee.getId());

        var expiration = Instant.now().plus(JwtConstants.ACCESS_EXPIRATION_MINUTES, ChronoUnit.MINUTES);

        var claims = JwtClaims.builder()
                .permissions(employeePermissions)
                .type(JwtConstants.ACCESS_TYPE_TOKEN)
                .build();

        log.info("Generating JWT for employee: {} finished", employee.getId());

        return generateJwt(employee.getId().toString(), claims, expiration);

    }

    @Override
    public String generateRefreshToken(UUID subjectId) {
        log.info("Generating refresh token for user: {}", subjectId);
        var expiration = Instant.now().plus(JwtConstants.REFRESH_EXPIRATION_DAYS, ChronoUnit.DAYS);

        var claims = JwtClaims.builder()
                .type(JwtConstants.REFRESH_TYPE_TOKEN)
                .build();


        log.info("Generating refresh token for user: {} finished", subjectId);

        return generateJwt(subjectId.toString(), claims, expiration);
    }

    @Override
    public String generateInternalJwt() {

        log.info("Generating internal JWT");

        var expiration = Instant.now().plus(JwtConstants.INTERNAL_EXPIRATION_MINUTES, ChronoUnit.MINUTES);

        var claims = JwtClaims.builder()
                .permissions(JwtConstants.INTERNAL_PERMISSIONS)
                .type(JwtConstants.INTERNAL_TYPE_TOKEN)
                .build();

        log.info("Generating internal JWT finished");

        return generateJwt(issuer, claims, expiration);
    }

    private String generateJwt(String subject, JwtClaims claims, Instant expiration) {
        Instant now = Instant.now();

        return Jwts.builder()
                .issuer(issuer)
                .subject(subject)
                .claims(toClaims(claims))
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(jwtSecret, Jwts.SIG.HS256)
                .compact();
    }

    private Map<String, Object> toClaims(JwtClaims claims) {
        return objectMapper.convertValue(claims, new TypeReference<>() {
        });
    }

}
