package com.example.Ecommerce.services;

import com.example.Ecommerce.user.Role;
import io.jsonwebtoken.Claims;

import java.util.Date;

public class Jwt {
    private final Claims claims;
    private final String token; // Store original token string


    public Jwt(Claims claims, String token){
        this.claims = claims;
        this.token = token;
    }

    public boolean isExpired() {
        return claims.getExpiration().before(new Date());
    }

    public Long getUserId() {
        return Long.valueOf(claims.getSubject());
    }

    public Role getRole() {
        return Role.valueOf(claims.get("role", String.class));
    }

    public String toString() {
        return token; // return original
    }
}
