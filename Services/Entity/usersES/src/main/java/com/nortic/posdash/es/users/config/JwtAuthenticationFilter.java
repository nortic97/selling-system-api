package com.nortic.posdash.es.users.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nortic.posdash.es.users.utils.ResponseBody;
import com.nortic.posdash.us.models.enums.StatusEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final ObjectMapper objectMapper;

    private final Environment env;

    @Autowired
    public JwtAuthenticationFilter(ObjectMapper objectMapper, Environment env) {
        this.objectMapper = objectMapper;
        this.env = env;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String SECRET_KEY = env.getProperty("SECRET_KEY");

        // Log the HTTP method and URL
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.warn("Missing or invalid Authorization header for request: {} {}", httpMethod, requestURI);
            sendErrorResponse(response, "Missing or invalid Authorization header", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authorizationHeader.substring(7);
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();

            // Check if token is expired
            Date expiration = claims.getExpiration();
            Date now = new Date();
            if (username != null && expiration != null && expiration.after(now)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, List.of());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Access granted for request: {} {}", httpMethod, requestURI);
            } else {
                log.warn("Token expired or invalid for user: {}", username);
                sendErrorResponse(response, "Token expired or invalid", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (ExpiredJwtException e) {
            log.warn("Token expired: {}", e.getMessage());
            sendErrorResponse(response, "Token expired", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (Exception e) {
            log.error("Failed to parse JWT token: {}", e.getMessage());
            sendErrorResponse(response, "Failed to parse JWT token", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String message, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new ResponseBody<>(message, StatusEnum.ERROR).getBody()));
    }
}
