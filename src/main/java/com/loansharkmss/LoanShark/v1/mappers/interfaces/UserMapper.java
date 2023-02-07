package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.*;
import com.loansharkmss.LoanShark.v1.model.User;

import java.util.List;

public interface UserMapper {
    User UserCreateToUser(UserCreate userCreate);

    UserFull UserToUserFull(User user);

    UserFullListResponse UserFullListToUserFullListResponse(List<UserFull> userFullList);

    UserMinimalCard UserToUserMinimal(User user);

    UserMinimalCardListResponse UserMinimalListToUserMinimalListResponse(List<UserMinimalCard> userMinimalCardList);

    UserProfileCard UserToUserProfileCard(User user);

    UserMinimalCardNoImage UserToUserMinimalCardNoImage(User user);
}
