package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.dtos.FriendIdsRequest;
import com.loansharkmss.LoanShark.v1.dtos.UserFull;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerV1
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserFull> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        UserFull userFull = userMapper.UserToUserFull(user);
        return ResponseEntity.ok(userFull);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserFull> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        UserFull userFull = userMapper.UserToUserFull(user);
        return ResponseEntity.ok(userFull);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserFull> findUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        UserFull userFull = userMapper.UserToUserFull(user);
        return ResponseEntity.ok(userFull);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/friends/request/{id}")
    public ResponseEntity<List<UserFull>> sendFriendRequests(@PathVariable Long id, @RequestBody FriendIdsRequest friendsIds) {
        List<User> updatedUsers = userService.sendFriendRequests(id, friendsIds.getFriendsIds());
        List<UserFull> updatedUserFullList = updatedUsers.stream().map(userMapper::UserToUserFull).collect(Collectors.toList());
        return ResponseEntity.ok(updatedUserFullList);
    }

    @PutMapping("/friends/request/{id}/accept/{idAcceptedFriend}")
    public ResponseEntity<List<UserFull>> acceptFriendRequest(@PathVariable Long id, @PathVariable Long idAcceptedFriend) {
        List<User> updatedUsers = userService.acceptFriendRequest(id, idAcceptedFriend);
        List<UserFull> updatedUserFullList = updatedUsers.stream().map(userMapper::UserToUserFull).collect(Collectors.toList());
        return ResponseEntity.ok(updatedUserFullList);
    }

}
