package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface AuthService {

    String generateJwt(User user);

    String getUsernameFromJwt(String jwt);

    Date getExpirationDateFromJwt(String jwt);

    Boolean isJwtExpired(String jwt);
    
    String login(UserLogin userLogin);

}
