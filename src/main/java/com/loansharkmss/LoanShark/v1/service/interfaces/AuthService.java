package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.model.User;

import java.util.Date;

public interface AuthService {

    public String generateJwt(User user);

    public String getUsernameFromJwt(String jwt);

    public Date getExpirationDateFromJwt(String jwt);

    public Boolean isJwtExpired(String jwt);

    public String login(UserLogin userLogin);

}
