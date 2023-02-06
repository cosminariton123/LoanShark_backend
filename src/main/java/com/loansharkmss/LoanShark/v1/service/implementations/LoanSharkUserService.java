package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.BadRequest;
import com.loansharkmss.LoanShark.v1.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.exceptions.Unauthorized;
import com.loansharkmss.LoanShark.v1.model.Image;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.UserRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.ImageService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import com.loansharkmss.LoanShark.v1.util.PasswordEncryption;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanSharkUserService implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncryption passwordEncryption;

    private final ImageService imageService;

    public LoanSharkUserService(UserRepository userRepository, PasswordEncryption passwordEncryption, ImageService imageService) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
        this.imageService = imageService;
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

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Transactional
    public void deleteUserById(Long id) {
        findUserById(id);
        Integer deletedCount = userRepository.deleteUserById(id);

        if (deletedCount > 0)
            return;

        throw new InternalServerError("Failed to delete user with id " + id);
    }

    public List<User> sendFriendRequests(Long userId, List<Long> friendsIds) {
        User user = findUserById(userId);

        Set<Long> friendsIdsSet = new HashSet<>(friendsIds);
        if (friendsIdsSet.size() != friendsIds.size())
            throw new BadRequest("UserIds list has duplicates");

        List<User> friends = friendsIds.stream()
                .map(this::findUserById)
                .collect(Collectors.toList());

        for (User friend : friends)
            if (friend.getId() == userId)
                throw new BadRequest("Can't send friend request to self");

        for (User friend : friends)
            if (friend.getPendingFriendRequests().contains(user))
                throw new BadRequest("User with id " + friend.getId() + " already has a pending friend request from user with id " + user.getId());

        for (User friend : friends) {
            friend.getPendingFriendRequests().add(user);
        }

        return saveAll(friends);
    }

    public List<User> acceptFriendRequest(Long userId, Long friendRequestId) {
        User user = findUserById(userId);
        User newFriend = findUserById(friendRequestId);

        if (user.getId() == newFriend.getId())
            throw new BadRequest("Can't add self as friend");

        if (user.getFriends().contains(newFriend))
            throw new BadRequest("User with id " + newFriend.getId() + "already in users with id " + user.getId() + " friends list\n" +
                    "Note that this message should be impossible to reach meaning that there is a bug server side");

        if (!user.getPendingFriendRequests().contains(newFriend))
            throw new NotFoundException("User with id " + userId + " doesn't have a pending friend request from user with id " + newFriend.getId());

        user.getPendingFriendRequests().remove(newFriend);
        user.getFriends().add(newFriend);
        newFriend.getFriends().add(user);

        save(user);
        save(newFriend);

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(newFriend);
        return users;
    }

    public List<User> findAllFriendsForUserWithId(Long userId) {
        User user = findUserById(userId);
        return user.getFriends();
    }

    @Transactional
    public User updateUserImage(Long userId, Image image) {
        User user = findUserById(userId);

        if (user.getImage() != null){
            Long oldImageId = user.getImage().getId();

            user.setImage(null);
            save(user);

            imageService.deleteImageById(oldImageId);
        }

        Image savedImage = imageService.saveNewImage(image);
        user.setImage(savedImage);

        return save(user);
    }
}
