package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.config.JwtConfig;
import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.exceptions.Unauthorized;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.UserRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoanSharkAuthService implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public LoanSharkAuthService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

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

    public String login(UserLogin userLogin) {
        User user = userRepository.findUserByEmail(userLogin.getUsername_or_email());
        if (user == null)
            user = userRepository.findUserByUsername(userLogin.getUsername_or_email());

        try {
            if (user == null)
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername_or_email(), userLogin.getPassword()));
            else
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), userLogin.getPassword()));
        }
        catch (DisabledException e) {
            throw new Unauthorized("User disabled");
        }
        catch (BadCredentialsException e) {
            throw new Unauthorized("Invalid credentials");
        }

        assert user != null;

        return generateJwt(user);
    }
}
