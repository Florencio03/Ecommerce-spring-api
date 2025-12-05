package com.example.Ecommerce.services;

import com.example.Ecommerce.config.JwtConfig;

import com.example.Ecommerce.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    public Jwt generateAccessToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private Jwt generateToken(User user, long tokenExpiration) {

        long now = System.currentTimeMillis();

        // 1) Create claims
        Claims claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("role", user.getRole().name())
                .issuedAt(new Date(now))
                .expiration(new Date(now + tokenExpiration * 1000))
                .build();

        // 2) Build the token string ONCE
        String token = Jwts.builder()
                .claims(claims)
                .signWith(jwtConfig.getSecretKey())
                .compact();

        // 3) Return both claims + original token
        return new Jwt(claims, token);
    }

    public Jwt parseToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(jwtConfig.getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return new Jwt(claims, token);
        } catch (JwtException e) {
            return null;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
