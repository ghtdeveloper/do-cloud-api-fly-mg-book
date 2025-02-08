package com.vortechgroup.queen.skies.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationMinutes}")
    private long expirationMinutes;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMinutes * 60 * 1000);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()),SignatureAlgorithm.HS512)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return Optional.of( extractClaim(token, Claims::getSubject)).orElseThrow();
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
