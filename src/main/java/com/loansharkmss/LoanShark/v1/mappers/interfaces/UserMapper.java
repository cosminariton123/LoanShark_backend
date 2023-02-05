package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.*;
import com.loansharkmss.LoanShark.v1.model.User;

import java.util.List;

public interface UserMapper {
    User UserCreateToUser(UserCreate userCreate);

    UserFull UserToUserFull(User user);

    UserFullListResponse UserFullListToUserFullListResponse(List<UserFull> userFullList);

    UserMinimal UserToUserMinimal(User user);

    UserMinimalListResponse UserMinimalListToUserMinimalListResponse(List<UserMinimal> userMinimalList);
}
