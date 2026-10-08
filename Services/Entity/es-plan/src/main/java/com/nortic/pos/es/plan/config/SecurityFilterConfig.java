package com.nortic.pos.es.plan.config;


import com.nortic.pos.es.plan.security.SecurityConstants;
import com.nortic.pos.es.plan.security.exception.SecurityAccessDeniedHandler;
import com.nortic.pos.es.plan.security.exception.SecurityAuthenticationEntryPoint;
import com.nortic.pos.es.plan.security.jwt.JwtAuthenticationFilter;
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

import static com.nortic.pos.es.plan.security.SecurityConstants.*;

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
                        .requestMatchers(SecurityConstants.PUBLIC_ENDPOINTS).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                SecurityConstants.API_BASE_PATH,
                                SecurityConstants.PLAN_ID)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_READ,
                                SecurityConstants.PERMISSION_PLAN_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.POST,
                                SecurityConstants.API_BASE_PATH)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_CREATE,
                                SecurityConstants.PERMISSION_PLAN_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PUT,
                                SecurityConstants.PLAN_ID)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_UPDATE,
                                SecurityConstants.PERMISSION_PLAN_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.DELETE,
                                SecurityConstants.PLAN_ID)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_DELETE,
                                SecurityConstants.PERMISSION_PLAN_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PATCH,
                                SecurityConstants.PLAN_RESTORE)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_DELETE,
                                SecurityConstants.PERMISSION_PLAN_ALL,
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

        configuration.setAllowedOrigins(ALLOWED_ORIGINS);

        configuration.setAllowedMethods(ALLOWED_METHODS);

        configuration.setAllowedHeaders(ALLOWED_HEADERS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
