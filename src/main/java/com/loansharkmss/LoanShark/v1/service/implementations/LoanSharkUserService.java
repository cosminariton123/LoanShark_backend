package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.BadRequest;
import com.loansharkmss.LoanShark.v1.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.exceptions.Unauthorized;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.UserRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import com.loansharkmss.LoanShark.v1.util.PasswordEncryption;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoanSharkUserService implements UserService {

    UserRepository userRepository;

    PasswordEncryption passwordEncryption;

    public LoanSharkUserService(UserRepository userRepository, PasswordEncryption passwordEncryption) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public User findUserById(Long id) {
        User user = userRepository.findUserById(id);

        if (user == null)
            throw  new NotFoundException("User with id " + id + " not found");

        return user;
    }

    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);

        if (user == null)
            throw new NotFoundException("User with email " + email + " not found");

        return user;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user == null)
            throw new NotFoundException("User with username " + username + " not found");

        return user;
    }

    public User findUserByUsernameOrEmail(String username_or_email) {
        User user = userRepository.findUserByUsername(username_or_email);

        if (user == null)
            user = userRepository.findUserByEmail(username_or_email);

        if (user == null)
            throw new NotFoundException("User with username_or_email " + username_or_email + " not found");
        return user;
    }


    //SPECIAL METHOD FOR SECURITY
    //transactional to permit lazy loading
    @Transactional
    public User loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user == null)
            throw new Unauthorized("Invalid credentials");

        //lazy loading authorities
        user.getAuthorities();

        return user;
    }

    public User saveNewUser(User user) {
        User existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser != null)
            throw new BadRequest("User with email " + user.getEmail() + " already exists");

        existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser != null)
            throw new BadRequest("User with username " + user.getUsername() + " already exists");

        user.setPassword(passwordEncryption.encryptPassword(user.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        findUserById(id);
        Integer deletedCount = userRepository.deleteUserById(id);

        if (deletedCount > 0)
            return;

        throw new InternalServerError("Failed to delete user with id " + id);
    }

}
