package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.UserCreate;
import com.loansharkmss.LoanShark.v1.model.User;

public interface UserMapper {
    User TestModelCreateDTOToTestModel(UserCreate userCreate);
}
