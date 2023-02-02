package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.dtos.UserCreate;
import com.loansharkmss.LoanShark.v1.dtos.UserFull;
import com.loansharkmss.LoanShark.v1.dtos.UserFullListResponse;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.Role;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.RoleService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanSharkUserMapper implements UserMapper {

    private final RoleService roleService;

    public LoanSharkUserMapper (RoleService roleService) {
        this.roleService = roleService;
    }

    public User UserCreateToUser(UserCreate userCreate) {
        User user = new User();

        user.setEmail(userCreate.getEmail());
        user.setUsername(userCreate.getUsername());
        user.setPassword(userCreate.getPassword());
        user.setFirstName(userCreate.getFirstName());
        user.setLastName(userCreate.getLastName());

        user.getRoles().add(roleService.loadRoleByName("ROLE_CLIENT"));
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEnabled(true);

        return user;
    }

    public UserFull UserToUserFull(User user) {
        UserFull userFull = new UserFull(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                !user.isAccountNonExpired(),
                !user.isAccountNonLocked(),
                !user.isCredentialsNonExpired(),
                user.isEnabled()
        );

        userFull.getRoles().addAll(user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        userFull.getFriendsIds().addAll(user.getFriends().stream().map(User::getId).collect(Collectors.toList()));
        userFull.getPendingFriendRequestsUsersIds().addAll(user.getPendingFriendRequests().stream().map(User::getId).collect(Collectors.toList()));

        return userFull;
    }

    public UserFullListResponse UserFullListToUserFullListResponse(List<UserFull> userFullList) {
        return new UserFullListResponse(userFullList);
    }
}
