package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.User;

public interface UserService {

    public User findUserById(Integer id);

    public  User findUserByEmail(String email);

    public User findUserByUsername(String username);

    public User findUserByEmailOrUsername(String username_or_email);

    public User saveNewUser(User user);

    public User deleteUserById(Integer id);

}
