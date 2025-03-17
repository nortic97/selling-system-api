package com.nortic.posdash.us.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nortic.posdash.models.enums.StatusEnum;
import com.nortic.posdash.us.auth.utils.ResponseBody;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final ObjectMapper objectMapper;
    private final String secretKey;

    @Autowired
    public JwtAuthenticationFilter(ObjectMapper objectMapper, Environment env) {
        this.objectMapper = objectMapper;
        this.secretKey = env.getProperty("SECRET_KEY");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Request: {} {}", request.getMethod(), request.getRequestURI());

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            log.warn("Missing or malformed Authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length());

        try {
            Claims claims = validateToken(token);
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();

            if (username != null && expiration != null && expiration.after(new Date())) {
                setUpAuthentication(username, request);
                log.info("Access granted for request: {} {}", request.getMethod(), request.getRequestURI());
            } else {
                log.warn("Token expired or invalid for user: {}", username);
                sendErrorResponse(response, "Token expired or invalid");
                return;
            }
        } catch (ExpiredJwtException e) {
            log.warn("Token expired: {}", e.getMessage());
            sendErrorResponse(response, "Token expired");
            return;
        } catch (Exception e) {
            log.error("Failed to parse JWT token: {}", e.getMessage());
            sendErrorResponse(response, "Failed to parse JWT token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private void setUpAuthentication(String username, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username, null, List.of());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new ResponseBody<>(message, StatusEnum.ERROR).getBody()));
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
