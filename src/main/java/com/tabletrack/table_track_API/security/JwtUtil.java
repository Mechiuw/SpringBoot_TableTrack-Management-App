package com.tabletrack.table_track_API.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tabletrack.table_track_API.model.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${app.tabletrack.jwt.jwt-secret}")
    private String jwtSecret;

    @Value("${app.tabletrack.jwt.app-name}")
    private String appName;

    @Value("${app.tabletrack.jwt.jwt-expiration}")
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

    //TODO 3 : Get the user's information from the token we generate
    public Map<String,String> getUserInfoByToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId",decodedJWT.getSubject());
            userInfo.put("role",decodedJWT.getClaim("role").asString());
            return userInfo;
        } catch (JWTVerificationException e){
            throw new RuntimeException(e);
        }
    }

}
