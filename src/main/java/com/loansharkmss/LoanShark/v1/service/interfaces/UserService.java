package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User findUserById(Long id);

    User findUserByEmail(String email);

    public User findUserByUsername(String username);

    User findUserByUsernameOrEmail(String username_or_email);

    User loadUserByUsername(String username);

    User saveNewUser(User user);

    void deleteUserById(Long id);

}
