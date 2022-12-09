package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.User;

public interface UserService {

    public User findUserById(Integer id);

    public  User findUserByEmail(String email);

    public User findUserByUsername(String username);

    public Boolean isAnyUserWithEmail(String email);

    public Boolean isAnyUserWithUsername(String username);

    public User saveNewUser(User user);

    public User deleteUserById(Integer id);

}
