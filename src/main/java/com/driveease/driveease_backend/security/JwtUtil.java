

package com.driveease.driveease_backend.security;
//
//import io.jsonwebtoken.security.Keys;
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.impl.TextCodec;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//    @Value("${jwt.secret}") private String secret;
//    @Value("${jwt.expiration}") private long expiration;
//
//    public String generateToken(String username) {
//        Date now = new Date();
//        Date exp = new Date(now.getTime() + expiration);
//        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(exp)
//                .signWith(SignatureAlgorithm.HS256, secret).compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException ex) {
//            return false;
//        }
//    }
//}
//


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Generate JWT Token
    public String generateToken(String username) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expiration);
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Username
    public String getUsernameFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}