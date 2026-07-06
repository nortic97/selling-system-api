package com.nortic.pos.es.user.security.jwt;

import com.nortic.pos.es.user.security.jwt.model.JwtProperties;
import com.nortic.pos.es.user.security.jwt.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public JwtUser validateToken(String token) {

        SecretKey key = Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes()
        );

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return JwtUser.builder()
                .userId(claims.getSubject())
                .tokenType(
                        claims.get(
                                "type",
                                String.class
                        )
                )
                .permissions(
                        claims.get(
                                "permissions",
                                List.class
                        )
                )
                .build();
    }
}