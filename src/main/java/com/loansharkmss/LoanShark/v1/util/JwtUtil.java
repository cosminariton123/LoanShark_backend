package com.loansharkmss.LoanShark.v1.util;

import com.loansharkmss.LoanShark.v1.config.JwtConfig;
import com.loansharkmss.LoanShark.v1.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public String generateJwt(User user) {
        Date currentTime = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(System.currentTimeMillis() + JwtConfig.JWT_VALIDITY);

        return Jwts.builder()
                .setSubject("Authentication")
                .setAudience(JwtConfig.AUDIENCE)
                .setIssuer(JwtConfig.DOMAIN)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .setSubject(user.getUsername())
                .signWith(JwtConfig.SIGNATURE_ALGORITHM, JwtConfig.SECRET_KEY_FOR_JWT)
                .compact();
    }

    private Claims getAllClaimsFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(JwtConfig.SECRET_KEY_FOR_JWT).parseClaimsJws(jwt).getBody();
    }

    public String getUsernameFromJwt(String jwt) {
        Claims claims = getAllClaimsFromJwt(jwt);
        return claims.getSubject();
    }

    public Date getExpirationDateFromJwt(String jwt) {
        Claims claims = getAllClaimsFromJwt(jwt);
        return claims.getExpiration();
    }

    public Boolean isJwtExpired(String jwt) {
        Date expiration = getExpirationDateFromJwt(jwt);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    public Boolean isJwtValid(String jwt, User user) {
        String username = getUsernameFromJwt(jwt);
        return (username.equals(user.getUsername())) && (!isJwtExpired(jwt));
    }

}
