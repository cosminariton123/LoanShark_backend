package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserFullListResponse {

    private final List<UserFull> users = new ArrayList<>();

    public UserFullListResponse(List<UserFull> userFullList) {
        users.addAll(userFullList);
    }

    public List<UserFull> getUsers() {
        return users;
    }
}
