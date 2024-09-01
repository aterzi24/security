package com.example.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JwtService {
    private static final String HMAC_SHA_256 = "HmacSHA256";

    @Value("${app.secret-key}")
    private String secretKey;

    @Value("${app.jwt-expiration-ms}")
    private int jwtExpiration;

    private Claims claims;

    public String generateJwt(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isJwtValid(String jwt) {
        try {
            Claims extracted = extractAllClaims(jwt);
            claims = extracted;
            return !extracted.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64URL.decode(secretKey);
        return new SecretKeySpec(bytes, HMAC_SHA_256);
    }

    public String extractUsername() {
        return claims.getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
