package com.application.ecommerce.jwt;

import com.application.ecommerce.config.AppUserDetails;
import com.application.ecommerce.model.user.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
public class JwtTokenUtil {

    public JwtToken generateToken(UserDetails userDetails) {
        return generate(userDetails.getUsername());
    }

    public JwtToken generateToken(User user) {
        return generate(user.getUsername());
    }

    private JwtToken generate(String username) {
        Date expiredDate = new Date(System.currentTimeMillis() + JwtTokenConfig.JWT_EXPR);

        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(expiredDate)
                .signWith(JwtTokenConfig.getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        return JwtToken.builder()
                .token(jwt)
                .exprDate(expiredDate.getTime())
                .build();
    }

    public String getUsernameFromJWT(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtTokenConfig.getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return null;
    }

}
