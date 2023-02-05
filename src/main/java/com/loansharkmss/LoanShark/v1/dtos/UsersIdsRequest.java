package com.loansharkmss.LoanShark.v1.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UsersIdsRequest {

    @NotNull(message = "usersIds must not be null")
    @NotEmpty(message = "usersIds must not be empty")
    private List<Long> usersIds;

    public List<Long> getUsersIds() {
        return usersIds;
    }
}
