package com.nortic.pos.es.plan.security.jwt;

import com.nortic.pos.es.plan.security.jwt.model.JwtClaim;
import com.nortic.pos.es.plan.security.jwt.model.JwtProperties;
import com.nortic.pos.es.plan.security.jwt.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtUser validateToken(String token) {

        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());

        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return JwtUser.builder()
                .userId(claims.getSubject())
                .storeId(claims.get(JwtClaim.STORE_ID.getValue(), String.class))
                .branchId(claims.get(JwtClaim.BRANCH_ID.getValue(), String.class))
                .tokenType(claims.get(JwtClaim.TOKEN_TYPE.getValue(), String.class))
                .permissions(claims.get(JwtClaim.PERMISSIONS.getValue(), List.class))
                .build();
    }
}
