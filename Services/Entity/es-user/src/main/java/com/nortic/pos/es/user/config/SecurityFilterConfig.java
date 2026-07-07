package com.nortic.pos.es.user.config;

import com.nortic.pos.es.user.dto.response.ErrorResponseDTO;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.security.SecurityConstants;
import com.nortic.pos.es.user.security.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

import static com.nortic.pos.es.user.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SecurityConstants.PUBLIC_ENDPOINTS).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                SecurityConstants.API_BASE_PATH,
                                SecurityConstants.USER_SEARCH,
                                SecurityConstants.USER_ID)
                        .hasAuthority(SecurityConstants.PERMISSION_USER_READ)

                        .requestMatchers(HttpMethod.POST,
                                SecurityConstants.API_BASE_PATH,
                                SecurityConstants.USER_QUERY)
                        .hasAuthority(SecurityConstants.PERMISSION_USER_CREATE)

                        .requestMatchers(HttpMethod.PUT,
                                SecurityConstants.USER_ID,
                                SecurityConstants.USER_CHANGE_PASSWORD,
                                SecurityConstants.USER_CHANGE_USERNAME,
                                SecurityConstants.USER_CHANGE_EMAIL,
                                SecurityConstants.USER_CHANGE_PHONE,
                                SecurityConstants.USER_CHANGE_STATUS)
                        .hasAuthority(SecurityConstants.PERMISSION_USER_UPDATE)

                        .requestMatchers(HttpMethod.DELETE,
                                SecurityConstants.USER_ID)
                        .hasAuthority(SecurityConstants.PERMISSION_USER_DELETE)

                        .requestMatchers(HttpMethod.PUT,
                                SecurityConstants.USER_RESTORE)
                        .hasAuthority(SecurityConstants.PERMISSION_USER_DELETE)

                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new SecurityAccessDeniedHandler())
                        .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(USER_ALLOWED_ORIGINS);

        configuration.setAllowedMethods(USER_ALLOWED_METHODS);

        configuration.setAllowedHeaders(USER_ALLOWED_HEADERS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    private static class SecurityAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(jakarta.servlet.http.@NonNull HttpServletRequest request,
                           jakarta.servlet.http.HttpServletResponse response,
                           org.springframework.security.access.@NonNull AccessDeniedException accessDeniedException)
                throws IOException {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");

            var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
            String userPermissions = "None";

            if (authentication != null && !authentication.getAuthorities().isEmpty()) {
                userPermissions = authentication.getAuthorities().stream()
                        .map(Object::toString)
                        .toList()
                        .toString();
            }

            var errorResponse = ErrorResponseDTO.builder()
                    .code(UserExceptionCode.MISSING_PERMISSION.getCode())
                    .message(UserExceptionCode.MISSING_PERMISSION.getMessage() + " - User permissions: " + userPermissions)
                    .timestamp(java.time.OffsetDateTime.now())
                    .build();

            response.getWriter().write("{\"code\":\"" + errorResponse.getCode() +
                    "\",\"message\":\"" + errorResponse.getMessage() +
                    "\",\"timestamp\":\"" + errorResponse.getTimestamp() + "\"}");
        }
    }

    private static class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(jakarta.servlet.http.@NonNull HttpServletRequest request,
                             jakarta.servlet.http.HttpServletResponse response,
                             org.springframework.security.core.@NonNull AuthenticationException authException)
                throws IOException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            var errorResponse = ErrorResponseDTO.builder()
                    .code("UNAUTHORIZED")
                    .message("Authentication required")
                    .timestamp(java.time.OffsetDateTime.now())
                    .build();

            response.getWriter().write("{\"code\":\"" + errorResponse.getCode() +
                    "\",\"message\":\"" + errorResponse.getMessage() +
                    "\",\"timestamp\":\"" + errorResponse.getTimestamp() + "\"}");
        }
    }

}
