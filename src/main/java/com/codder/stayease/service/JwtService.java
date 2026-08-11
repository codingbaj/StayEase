package com.codder.stayease.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Keep this secret private.
    // It must be long enough for HMAC-SHA256.
    private final String secretKey =
            "StayEaseSecretKeyForJWTAuthentication2026VerySecure";

    private final long expirationTime = 1000 * 60 * 60 * 24; // 24 hours


    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }


    // Generate JWT
    public String generateToken(long userId,
                                String email,
                                String role) {

        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + expirationTime)
                )
                .signWith(getSigningKey())
                .compact();
    }


    // Extract email
    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }


    // Extract role
    public String extractRole(String token) {

        return getClaims(token)
                .get("role", String.class);
    }


    // Extract user ID
    public Long extractUserId(String token) {

        return getClaims(token)
                .get("userId", Long.class);
    }


    // Validate token
    public boolean isTokenValid(String token) {

        try {

            getClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }


    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}