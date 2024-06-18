package com.bahari.bahari_resto_API.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bahari.bahari_resto_API.model.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Component
public class JwtUtil {
    @Value("${app.shopee.jwt.jwt-secret")
    private String jwtSecret;

    @Value("${app.shopee.jwt.app-name")
    private String appName;

    @Value("${app.shopee.jwt.jwt-expiert")
    private long jwtExpiration;

    //TODO 1 : Make/Generate new token
    public String generateToken(AppUser appUser){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            String token = JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpiration))
                    .withIssuedAt(Instant.now())
                    .withClaim("role",appUser.getRole().name())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO 2 : Verify the token we just made
    public boolean verifyJwtToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (JWTVerificationException e){
            throw new RuntimeException(e);
        }
    }



}
