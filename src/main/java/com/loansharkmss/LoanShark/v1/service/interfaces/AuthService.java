package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface AuthService {
    
    String login(UserLogin userLogin);

}
