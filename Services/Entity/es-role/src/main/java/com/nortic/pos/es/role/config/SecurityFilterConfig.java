package com.nortic.pos.es.role.config;


import com.nortic.pos.es.role.security.SecurityConstants;
import com.nortic.pos.es.role.security.exception.SecurityAccessDeniedHandler;
import com.nortic.pos.es.role.security.exception.SecurityAuthenticationEntryPoint;
import com.nortic.pos.es.role.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final SecurityAccessDeniedHandler accessDeniedHandler;
    private final SecurityAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                SecurityConstants.PUBLIC_ENDPOINTS
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                SecurityConstants.API_BASE_PATH,
                                SecurityConstants.ROLE_ID,
                                SecurityConstants.ROLE_PERMISSION)
                        .hasAnyAuthority(
                                SecurityConstants.ROLE_READ,
                                SecurityConstants.PERMISSION_ROLE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.POST,
                                SecurityConstants.API_BASE_PATH,
                                SecurityConstants.ROLE_PERMISSION)
                        .hasAnyAuthority(
                                SecurityConstants.ROLE_CREATE,
                                SecurityConstants.PERMISSION_ROLE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PUT,
                                SecurityConstants.ROLE_ID)
                        .hasAnyAuthority(
                                SecurityConstants.ROLE_UPDATE,
                                SecurityConstants.PERMISSION_ROLE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PATCH,
                                SecurityConstants.ROLE_CHANGE_STATUS)
                        .hasAnyAuthority(
                                SecurityConstants.ROLE_UPDATE,
                                SecurityConstants.PERMISSION_ROLE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.DELETE,
                                SecurityConstants.ROLE_ID,
                                SecurityConstants.ROLE_PERMISSION,
                                SecurityConstants.ROLE_PERMISSION_ID)
                        .hasAnyAuthority(
                                SecurityConstants.ROLE_DELETE,
                                SecurityConstants.PERMISSION_ROLE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PATCH,
                                SecurityConstants.ROLE_RESTORE)
                        .hasAnyAuthority(
                                SecurityConstants.ROLE_DELETE,
                                SecurityConstants.PERMISSION_ROLE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint)
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
        configuration.setAllowedOrigins(SecurityConstants.ALLOWED_ORIGINS);
        configuration.setAllowedMethods(SecurityConstants.ALLOWED_METHODS);
        configuration.setAllowedHeaders(SecurityConstants.ALLOWED_HEADERS);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
