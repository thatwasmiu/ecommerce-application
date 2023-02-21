package com.application.ecommerce.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenConfig {
    private static final String SIGN_IN_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public static final int JWT_EXPR = 1000 * 60 * 60 * 24;

    public static final int JWT_REFRESH_EXPR = 1000 * 60 * 60 * 2 * 14;

    public static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SIGN_IN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
