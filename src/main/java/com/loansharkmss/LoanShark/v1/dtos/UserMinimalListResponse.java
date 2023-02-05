package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserMinimalListResponse {

    private final List<UserMinimal> users = new ArrayList<>();

    public UserMinimalListResponse(List<UserMinimal> userMinimalList) {
        users.addAll(userMinimalList);
    }

    public List<UserMinimal> getUsers() {
        return users;
    }

}
