package com.study.websocketstompsock.auth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    @Value("${jwt.access.expiration}")
    private long accessTokenExpiration;
    private final SecretKey secretKey;

    private static final String ISSUER = "changhyeon";

    public JwtProvider(@Value("${jwt.secret}") final String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
    }

    public String generateAccessToken(final String email) {
        return Jwts.builder()
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (accessTokenExpiration * 1000)))
                .subject(email)
                .signWith(secretKey)
                .compact();
    }

    public String getSubject(final String token) {
        return parseToken(token)
                .getPayload()
                .getSubject();
    }

    private Jws<Claims> parseToken(final String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }

    public void verifyToken(final String token) {
        parseToken(token);
    }
}
