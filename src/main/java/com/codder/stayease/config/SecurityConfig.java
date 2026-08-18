package com.codder.stayease.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // =====================================================
                // CORS
                // =====================================================

                .cors(cors ->
                        cors.configurationSource(
                                corsConfigurationSource
                        )
                )

                // =====================================================
                // CSRF
                // =====================================================

                .csrf(csrf -> csrf.disable())

                // =====================================================
                // STATELESS JWT
                // =====================================================

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // =====================================================
                // AUTHORIZATION
                // =====================================================

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC
                        .requestMatchers("/auth/**")
                        .permitAll()

                        // =================================================
                        // OPTIONS / CORS PREFLIGHT
                        // =================================================

                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()

                        // =================================================
                        // PASSWORD
                        // =================================================

                        .requestMatchers("/user/change-password")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF",
                                "TENANT"
                        )

                        // =================================================
                        // USER
                        // =================================================

                        .requestMatchers("/user/**")
                        .hasRole("ADMIN")

                        // =================================================
                        // TENANT
                        // =================================================

                        .requestMatchers("/tenant/me")
                        .hasRole("TENANT")

                        .requestMatchers("/tenant/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // RENT
                        // =================================================

                        .requestMatchers("/rent/my/**")
                        .hasRole("TENANT")

                        .requestMatchers("/rent/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // PAYMENT
                        // =================================================

                        .requestMatchers("/payment/my/**")
                        .hasRole("TENANT")

                        .requestMatchers("/payment/pay")
                        .hasRole("TENANT")

                        .requestMatchers("/payment/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // COMPLAINT
                        // =================================================

                        .requestMatchers("/complaint/my/**")
                        .hasRole("TENANT")

                        .requestMatchers("/complaint/my")
                        .hasRole("TENANT")

                        .requestMatchers("/complaint/add")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/complaint/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // VISITOR
                        // =================================================

                        .requestMatchers("/visitor/my/**")
                        .hasRole("TENANT")

                        .requestMatchers("/visitor/my")
                        .hasRole("TENANT")

                        .requestMatchers("/visitor/add")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/visitor/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // ALLOCATION
                        // =================================================

                        .requestMatchers("/allocation/my/**")
                        .hasRole("TENANT")

                        .requestMatchers("/allocation/my")
                        .hasRole("TENANT")

                        .requestMatchers("/allocation/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // NOTICE
                        // =================================================

                        .requestMatchers(HttpMethod.GET, "/notice/**")
                        .authenticated()

                        .requestMatchers("/notice/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        // =================================================
                        // BUILDING
                        // =================================================

                        .requestMatchers(HttpMethod.GET, "/building/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/building/**")
                        .hasRole("ADMIN")

                        // =================================================
                        // FLOOR
                        // =================================================

                        .requestMatchers(HttpMethod.GET, "/floor/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/floor/**")
                        .hasRole("ADMIN")

                        // =================================================
                        // ROOM TYPE
                        // =================================================

                        .requestMatchers(HttpMethod.GET, "/roomtype/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/roomtype/**")
                        .hasRole("ADMIN")

                        // =================================================
                        // ROOM
                        // =================================================

                        .requestMatchers(HttpMethod.GET, "/room/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/room/**")
                        .hasRole("ADMIN")

                        // =================================================
                        // BED
                        // =================================================

                        .requestMatchers(HttpMethod.GET, "/bed/**")
                        .hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers("/bed/**")
                        .hasRole("ADMIN")

                        // =================================================
                        // EVERYTHING ELSE
                        // =================================================

                        .anyRequest()
                        .authenticated()
                )

                // =====================================================
                // JWT FILTER
                // =====================================================

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}