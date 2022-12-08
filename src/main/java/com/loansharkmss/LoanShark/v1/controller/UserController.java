package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.dtos.UserCreateDTO;
import com.loansharkmss.LoanShark.v1.mappers.UserMapper;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestControllerV1
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("")
    public ResponseEntity<User> saveNewUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        User user = userMapper.TestModelCreateDTOToTestModel(userCreateDTO);
        User savedUser = userService.saveNewUser(user);
        return ResponseEntity.created(URI.create("/user/" + savedUser.getId())).body(savedUser);
    }

    @DeleteMapping("")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id) {
        User deletedUser = userService.deleteUserById(id);
        return ResponseEntity.ok().body(deletedUser);
    }
}
