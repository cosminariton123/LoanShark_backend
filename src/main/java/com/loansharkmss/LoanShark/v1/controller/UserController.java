package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestControllerV1
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
