package com.loansharkmss.LoanShark.service;

import com.loansharkmss.LoanShark.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.model.User;
import com.loansharkmss.LoanShark.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Integer id) {
        User user = userRepository.findUserById(id);

        if (user == null)
            throw  new NotFoundException("User with id " + id + " not found");

        return user;
    }

    public User saveNewUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User deleteUserById(Integer id) {
        User user = findUserById(id);
        Integer deletedCount = userRepository.deleteUserById(id);

        if (deletedCount > 0)
            return user;

        throw new InternalServerError("Failed to delete user with id " + id);
    }

}
