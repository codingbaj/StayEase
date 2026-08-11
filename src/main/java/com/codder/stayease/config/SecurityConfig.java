package com.codder.stayease.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PUBLIC
                        // =========================
                        .requestMatchers("/auth/**").permitAll()

                        // =========================
                        // USER MANAGEMENT - ADMIN ONLY
                        // =========================
                        .requestMatchers("/user/**").hasRole("ADMIN")

                        // =========================
                        // TENANT PROFILE
                        // =========================
                        .requestMatchers("/tenant/me").hasRole("TENANT")
                        .requestMatchers("/tenant/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // RENT
                        // =========================
                        .requestMatchers("/rent/my/**").hasRole("TENANT")
                        .requestMatchers("/rent/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // PAYMENT
                        // =========================
                        .requestMatchers("/payment/my/**").hasRole("TENANT")
                        .requestMatchers("/payment/pay").hasRole("TENANT")
                        .requestMatchers("/payment/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // COMPLAINT - TENANT OWN DATA
                        // =========================
                        .requestMatchers("/complaint/my/**").hasRole("TENANT")
                        .requestMatchers("/complaint/my").hasRole("TENANT")
                        // Tenant creates only through /my/add; management uses /complaint/add
                        .requestMatchers("/complaint/add").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/complaint/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // VISITOR - TENANT OWN DATA
                        // =========================
                        .requestMatchers("/visitor/my/**").hasRole("TENANT")
                        .requestMatchers("/visitor/my").hasRole("TENANT")
                        .requestMatchers("/visitor/add").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/visitor/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // ALLOCATION
                        // Tenant can only view own allocation.
                        // Admin/Staff manage allocations.
                        // =========================
                        .requestMatchers("/allocation/my/**").hasRole("TENANT")
                        .requestMatchers("/allocation/my").hasRole("TENANT")
                        .requestMatchers("/allocation/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // NOTICE
                        // Everyone authenticated can read notices.
                        // Admin/Staff can manage notices.
                        // =========================
                        .requestMatchers(HttpMethod.GET, "/notice/**").authenticated()
                        .requestMatchers("/notice/**").hasAnyRole("ADMIN", "STAFF")

                        // =========================
                        // PROPERTY / INVENTORY
                        // ADMIN manages; STAFF can read.
                        // =========================
                        .requestMatchers(HttpMethod.GET, "/building/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/building/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/floor/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/floor/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/roomtype/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/roomtype/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/room/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/room/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/bed/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/bed/**").hasRole("ADMIN")

                        // Anything not explicitly listed requires authentication.
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
