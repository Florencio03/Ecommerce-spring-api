package com.example.Ecomerce.services;

import com.example.Ecomerce.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class Jwt {
    private final Claims claims;
    private final String token;


    public Jwt(Claims claims, String token){
        this.claims = claims;
        this.token = token;
    }

    public boolean isExpired(){
        return claims.getExpiration().before(new Date());
    }

    public Long getUserId(){
        return Long.valueOf(claims.getSubject());
    }

    public Role getRole(){
        return Role.valueOf(claims.get("role", String.class));
    }

    public String toString() {
        return token; // return original
    }

}
