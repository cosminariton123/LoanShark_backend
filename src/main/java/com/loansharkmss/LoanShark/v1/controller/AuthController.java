package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.dtos.JwtResponse;
import com.loansharkmss.LoanShark.v1.dtos.UserCreate;
import com.loansharkmss.LoanShark.v1.dtos.UserFull;
import com.loansharkmss.LoanShark.v1.dtos.UserLogin;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.AuthService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestControllerV1
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    private final UserMapper userMapper;

    private final UserService userService;

    public AuthController(AuthService authService, UserMapper userMapper, UserService userService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid UserLogin userLogin) {
        String jwt = authService.login(userLogin);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<UserFull> saveNewUser(@RequestBody @Valid UserCreate userCreate) {
        User user = userMapper.UserCreateToUser(userCreate);
        User savedUser = userService.saveNewUser(user);
        UserFull userFull = userMapper.UserToUserFull(savedUser);
        return ResponseEntity.created(URI.create("/user/" + savedUser.getId())).body(userFull);
    }

}
