package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.config.JwtConfig;
import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.exceptions.Unauthorized;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.UserRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoanSharkAuthService implements AuthService {

    private UserRepository userRepository;

    public LoanSharkAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateJwt(User user) {
        Date currentTime = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(System.currentTimeMillis() + JwtConfig.JWT_VALIDITY);

        return Jwts.builder()
                .setSubject("Authentication")
                .setAudience(JwtConfig.audience)
                .setIssuer(JwtConfig.domain)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .setSubject(user.getUsername())
                .signWith(JwtConfig.algorithm, JwtConfig.secret_key_for_jwt)
                .compact();
    }

    private Claims getAllClaimsFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(JwtConfig.secret_key_for_jwt).parseClaimsJws(jwt).getBody();
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

    public String login(UserLogin userLogin) {
        User user = userRepository.findUserByEmail(userLogin.getUsername_or_email());
        if (user == null)
            user = userRepository.findUserByUsername(userLogin.getUsername_or_email());

        if (user == null)
            throw new Unauthorized("Invalid Credentials");

        if (userLogin // ENCRYPT)

        String jwt = authService.generateJwt(user);
    }
}
