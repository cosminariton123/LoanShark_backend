package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    
    String login(UserLogin userLogin);

}
