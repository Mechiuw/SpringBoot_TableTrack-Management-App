package com.bahari.bahari_resto_API.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${app.shopee.jwt.jwt-secret")
    private String jwtSecret;

    @Value("${app.shopee.jwt.app-name")
    private String appName;

    @Value("${app.shopee.jwt.jwt-expiert")
    private long jwtExpiration;

    //TODO 1 : Generate tokens
    public String generateToken(AppUser appUser){

    }

}
