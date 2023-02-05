package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserMinimalCardListResponse {

    private final List<UserMinimalCard> users = new ArrayList<>();

    public UserMinimalCardListResponse(List<UserMinimalCard> userMinimalCardList) {
        users.addAll(userMinimalCardList);
    }

    public List<UserMinimalCard> getUsers() {
        return users;
    }

}
