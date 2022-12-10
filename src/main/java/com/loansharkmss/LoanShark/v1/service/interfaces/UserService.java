package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User loadUserById(Long id);

    User loadUserByEmail(String email);

    User loadUserByUsername(String username);

    User loadUserByUsernameOrEmail(String username_or_email);

    User saveNewUser(User user);

    User deleteUserById(Long id);

}
