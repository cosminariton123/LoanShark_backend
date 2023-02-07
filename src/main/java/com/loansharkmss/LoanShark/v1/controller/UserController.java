package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.dtos.*;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.ImageMapper;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.Image;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.ImageService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestControllerV1
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final ImageMapper imageMapper;

    public UserController(UserService userService, UserMapper userMapper, ImageMapper imageMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.imageMapper = imageMapper;
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
    public ResponseEntity<UserFullListResponse> sendFriendRequests(@PathVariable Long id, @RequestBody @Valid UsersIdsRequest friendsIds) {
        List<User> updatedUsers = userService.sendFriendRequests(id, friendsIds.getUsersIds());
        List<UserFull> updatedUserFullList = updatedUsers.stream().map(userMapper::UserToUserFull).collect(Collectors.toList());
        UserFullListResponse updatedUserFullListResponse = userMapper.UserFullListToUserFullListResponse(updatedUserFullList);
        return ResponseEntity.ok(updatedUserFullListResponse);
    }

    @PutMapping("/friends/request/{id}/accept/{idAcceptedFriend}")
    public ResponseEntity<UserFullListResponse> acceptFriendRequest(@PathVariable Long id, @PathVariable Long idAcceptedFriend) {
        List<User> updatedUsers = userService.acceptFriendRequest(id, idAcceptedFriend);
        List<UserFull> updatedUserFullList = updatedUsers.stream().map(userMapper::UserToUserFull).collect(Collectors.toList());
        UserFullListResponse updatedUserFullListResponse = userMapper.UserFullListToUserFullListResponse(updatedUserFullList);
        return ResponseEntity.ok(updatedUserFullListResponse);
    }

    @PutMapping("/friends/request/{id}/decline/{idDeclinedFriend}")
    public ResponseEntity<UserFull> declineFriendRequest(@PathVariable Long id, @PathVariable Long idDeclinedFriend) {
        User updatedUser = userService.declineFriendRequest(id, idDeclinedFriend);
        UserFull updatedUserFull = userMapper.UserToUserFull(updatedUser);
        return ResponseEntity.ok(updatedUserFull);
    }

    @GetMapping("/friends/{id}")
    public ResponseEntity<UserMinimalCardListResponse> findAllFriends(@PathVariable Long id) {
       List<User> users = userService.findAllFriendsForUserWithId(id);
       List<UserMinimalCard> userMinimalCardList = users.stream().map(userMapper::UserToUserMinimal).collect(Collectors.toList());
       UserMinimalCardListResponse userMinimalCardListResponse = userMapper.UserMinimalListToUserMinimalListResponse(userMinimalCardList);
       return ResponseEntity.ok(userMinimalCardListResponse);
    }

    @PutMapping("/set/profile/picture/{userid}")
    public ResponseEntity<UserMinimalCard> updateUserImage(@PathVariable Long userid, @RequestBody ImageCreateRequest imageCreateRequest) {
        Image image = imageMapper.ImageCreateRequestToImage(imageCreateRequest);
        User user = userService.updateUserImage(userid, image);
        UserMinimalCard userMinimalCard = userMapper.UserToUserMinimal(user);
        return ResponseEntity.ok(userMinimalCard);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileCard> findUserProfile(@PathVariable Long id) {
        User user = userService.findUserById(id);
        UserProfileCard userProfileCard = userMapper.UserToUserProfileCard(user);
        return ResponseEntity.ok(userProfileCard);
    }

}
