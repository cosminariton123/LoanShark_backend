package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.UserCreate;
import com.loansharkmss.LoanShark.v1.dtos.UserFull;
import com.loansharkmss.LoanShark.v1.model.User;

public interface UserMapper {
    User UserCreateToUser(UserCreate userCreate);

    UserFull UserToUserFull(User user);

}
