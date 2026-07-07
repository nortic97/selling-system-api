package com.nortic.pos.es.user.config;

import com.nortic.pos.es.user.security.SecurityConstants;
import com.nortic.pos.es.user.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SecurityConstants.PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(SecurityConstants.USER_GET_ALL).hasAuthority(SecurityConstants.PERMISSION_USER_READ)
                        .requestMatchers(SecurityConstants.USER_CREATE).hasAuthority(SecurityConstants.PERMISSION_USER_CREATE)
                        .requestMatchers(SecurityConstants.USER_SEARCH).hasAuthority(SecurityConstants.PERMISSION_USER_READ)
                        .requestMatchers(SecurityConstants.USER_QUERY).hasAuthority(SecurityConstants.PERMISSION_USER_READ)
                        .requestMatchers(SecurityConstants.USER_BY_ID).hasAuthority(SecurityConstants.PERMISSION_USER_READ)
                        .requestMatchers(SecurityConstants.USER_UPDATE).hasAuthority(SecurityConstants.PERMISSION_USER_UPDATE)
                        .requestMatchers(SecurityConstants.USER_DELETE).hasAuthority(SecurityConstants.PERMISSION_USER_DELETE)
                        .requestMatchers(SecurityConstants.USER_RESTORE).hasAuthority(SecurityConstants.PERMISSION_USER_RESTORE)
                        .requestMatchers(SecurityConstants.USER_CHANGE_PASSWORD).hasAuthority(SecurityConstants.PERMISSION_USER_CHANGE_PASSWORD)
                        .requestMatchers(SecurityConstants.USER_CHANGE_USERNAME).hasAuthority(SecurityConstants.PERMISSION_USER_CHANGE_USERNAME)
                        .requestMatchers(SecurityConstants.USER_CHANGE_EMAIL).hasAuthority(SecurityConstants.PERMISSION_USER_CHANGE_EMAIL)
                        .requestMatchers(SecurityConstants.USER_CHANGE_PHONE).hasAuthority(SecurityConstants.PERMISSION_USER_CHANGE_PHONE)
                        .requestMatchers(SecurityConstants.USER_CHANGE_STATUS).hasAuthority(SecurityConstants.PERMISSION_USER_CHANGE_STATUS)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}
