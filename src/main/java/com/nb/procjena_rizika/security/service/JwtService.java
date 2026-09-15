package com.nb.procjena_rizika.security.service;


import com.nb.procjena_rizika.security.dto.JwtDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

//    private final String SECRET = "5f090c21d7de7b2b5c33695b1a07233186ee39c3c4281119903bcf3221aec2b3";

    @Value("${jwt.secret}")
    String jwtSecret;

    @Value("${jwt.ttl}")
    Long jwtTtl;

    @Value("${jwt.refresh}")
    Integer jwtRefresh;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public JwtDto generate(Authentication authentication, Boolean rememberMe) {
        String subject= authentication.getPrincipal().toString();
        String token, refreshToken;
        refreshToken=null;
        token = Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000* 60 * jwtTtl))
                .signWith(getSecretKey())
                .compact();


        if (rememberMe){
            refreshToken = Jwts.builder()
                    .subject(subject)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis()+1000* 60 * jwtRefresh))
                    .signWith(getSecretKey())
                    .compact();


        }

        return new JwtDto(token, refreshToken);




    }
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validate(String token, UserDetails userDetails) {
        if(userDetails.getUsername().equals(getUsername(token)))
            return true;
        return false;

    }

}
