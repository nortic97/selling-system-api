package com.nortic.pos.es.employee.config;

import com.nortic.pos.es.employee.security.SecurityConstants;
import com.nortic.pos.es.employee.security.exception.SecurityAccessDeniedHandler;
import com.nortic.pos.es.employee.security.exception.SecurityAuthenticationEntryPoint;
import com.nortic.pos.es.employee.security.jwt.JwtAuthenticationFilter;
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

import static com.nortic.pos.es.employee.security.SecurityConstants.EMPLOYEE_ALLOWED_HEADERS;
import static com.nortic.pos.es.employee.security.SecurityConstants.EMPLOYEE_ALLOWED_METHODS;
import static com.nortic.pos.es.employee.security.SecurityConstants.EMPLOYEE_ALLOWED_ORIGINS;

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
                                SecurityConstants.EMPLOYEE_SEARCH,
                                SecurityConstants.EMPLOYEE_ID)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_EMPLOYEE_READ,
                                SecurityConstants.PERMISSION_EMPLOYEE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.POST,
                                SecurityConstants.API_BASE_PATH,
                                SecurityConstants.EMPLOYEE_QUERY)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_EMPLOYEE_CREATE,
                                SecurityConstants.PERMISSION_EMPLOYEE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PUT,
                                SecurityConstants.EMPLOYEE_ID)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_EMPLOYEE_UPDATE,
                                SecurityConstants.PERMISSION_EMPLOYEE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.PATCH,
                                SecurityConstants.EMPLOYEE_CHANGE_PASSWORD,
                                SecurityConstants.EMPLOYEE_CHANGE_USERNAME,
                                SecurityConstants.EMPLOYEE_CHANGE_EMAIL,
                                SecurityConstants.EMPLOYEE_CHANGE_PHONE,
                                SecurityConstants.EMPLOYEE_CHANGE_STATUS)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_EMPLOYEE_UPDATE,
                                SecurityConstants.PERMISSION_EMPLOYEE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)

                        .requestMatchers(HttpMethod.DELETE,
                                SecurityConstants.EMPLOYEE_ID)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_EMPLOYEE_DELETE,
                                SecurityConstants.PERMISSION_EMPLOYEE_ALL,
                                SecurityConstants.PERMISSION_SYSTEM_ALL)


                        .requestMatchers(HttpMethod.PATCH,
                                SecurityConstants.EMPLOYEE_RESTORE)
                        .hasAnyAuthority(
                                SecurityConstants.PERMISSION_EMPLOYEE_DELETE,
                                SecurityConstants.PERMISSION_EMPLOYEE_ALL,
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

        configuration.setAllowedOrigins(EMPLOYEE_ALLOWED_ORIGINS);

        configuration.setAllowedMethods(EMPLOYEE_ALLOWED_METHODS);

        configuration.setAllowedHeaders(EMPLOYEE_ALLOWED_HEADERS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
