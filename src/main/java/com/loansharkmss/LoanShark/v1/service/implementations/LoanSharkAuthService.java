package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.exceptions.Unauthorized;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.UserRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.AuthService;
import com.loansharkmss.LoanShark.v1.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoanSharkAuthService implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public LoanSharkAuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
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

        return jwtUtil.generateJwt(user);
    }
}
