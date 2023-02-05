package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserFull {

    private final Long id;

    private final String email;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final List<Long> roles = new ArrayList<>();

    private final List<Long> friendsIds = new ArrayList<>();

    private final List<Long> pendingFriendRequestsUsersIds = new ArrayList<>();

    private final Boolean accountNonExpired;

    private final Boolean accountNonLocked;

    private final Boolean credentialsNonExpired;

    private final Boolean enabled;

    public UserFull(Long id, String email, String username, String firstName, String lastName, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public List<Long> getFriendsIds() {
        return friendsIds;
    }

    public List<Long> getPendingFriendRequestsUsersIds() {
        return pendingFriendRequestsUsersIds;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
