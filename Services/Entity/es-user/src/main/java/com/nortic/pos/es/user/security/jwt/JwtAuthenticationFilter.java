package com.nortic.pos.es.user.security.jwt;

import com.nortic.pos.es.user.security.jwt.model.JwtUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");

            return;
        }

        String token = authorization.substring(7).trim();

        JwtUser jwtUser = jwtService.validateToken(token);

        request.setAttribute("jwtUser", jwtUser);

        filterChain.doFilter(request, response);
    }
}
