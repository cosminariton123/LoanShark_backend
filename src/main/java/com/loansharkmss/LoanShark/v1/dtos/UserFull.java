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

    private final Boolean accountExpired;

    private final Boolean accountLocked;

    private final Boolean credentialsExpired;

    private final Boolean enabled;

    public UserFull(Long id, String email, String username, String firstName, String lastName, Boolean accountExpired, Boolean accountLocked, Boolean credentialsExpired, Boolean enabled) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
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

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
