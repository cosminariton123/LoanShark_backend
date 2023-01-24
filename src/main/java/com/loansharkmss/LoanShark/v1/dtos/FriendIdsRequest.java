package com.loansharkmss.LoanShark.v1.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FriendIdsRequest {

    @NotNull
    @NotEmpty
    private final List<Long> friendsIds = new ArrayList<>();

    public List<Long> getFriendsIds() {
        return friendsIds;
    }
}
